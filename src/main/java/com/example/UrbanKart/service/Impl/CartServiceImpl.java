package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.dto.RequestDto.CheckoutCartRequestDto;
import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CartResponseDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.EmptyCartException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.model.*;
import com.example.UrbanKart.repository.*;
import com.example.UrbanKart.service.CartService;
import com.example.UrbanKart.service.OrderService;
import com.example.UrbanKart.transformer.CartTransformer;
import com.example.UrbanKart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();

        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity() * product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart);//will save both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);

        product.getItems().add(savedItem);
        //prepare response dto
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {

        //check customer
        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exist!");
        }
        // check card
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card == null || card.getCvv() != checkoutCartRequestDto.getCvv() || date.after(card.getValidTill())) {
            throw new InvalidCardException("Card can't be used. Please check the details!");
        }

        //check if cart is empty
        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0){
            throw new EmptyCartException("Cart is empty!!");
        }

        //create order
        try{
            OrderEntity order = orderService.placeOrder(cart, card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);
            return OrderTransformer.OrderToOrderResponseDto(savedOrder);

        }
        catch(InsufficientQuantityException e){
            throw e;
        }

    }
     private void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
     }
}
