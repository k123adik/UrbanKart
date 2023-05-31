package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.Enum.ProductStatus;
import com.example.UrbanKart.dto.RequestDto.OrderRequestDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.exception.ProductNotFoundException;
import com.example.UrbanKart.model.*;
import com.example.UrbanKart.repository.CardRepository;
import com.example.UrbanKart.repository.CustomerRepository;
import com.example.UrbanKart.repository.OrderRepository;
import com.example.UrbanKart.repository.ProductRepository;
import com.example.UrbanKart.service.OrderService;
import com.example.UrbanKart.transformer.ItemTransformer;
import com.example.UrbanKart.transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException {

        //check customer is present in db
        Customer customer = customerRepository.findByEmailId((orderRequestDto.getEmailId()));
        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exists!");
        }
        //check product is present or not in db
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product does not exist!");
        }

        Product product = optionalProduct.get();

        //check quantity
        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Required quantity not available");
        }

        //check card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Card can't be used. Please check the details!");
        }

        //decrease product quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        //create item
        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

        OrderEntity orderEntity = OrderTransformer.OrderRequestDtoToOrder(item, customer);
        String maskedCard = generateMaskedCardNo(card);
        orderEntity.setCardUsed(maskedCard);
        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity); //saves both order and item

        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        //prepare response dto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }

    public  OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));

        int totalValue = 0;
        for(Item item: cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity() > product.getQuantity()){
                throw new InsufficientQuantityException("Required quantity not available");
            }

            totalValue += item.getRequiredQuantity()*product.getPrice();
            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if(newQuantity == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            item.setOrderEntity(orderEntity);
        }
        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    private String generateMaskedCardNo(Card card){
        String cardNo = "";
        String originalCardNo = card.getCardNo();

        for(int i=0; i<originalCardNo.length()-4; i++){
            cardNo += "X";
        }

        cardNo += originalCardNo.substring(originalCardNo.length()-4);
        return cardNo;
    }
}
