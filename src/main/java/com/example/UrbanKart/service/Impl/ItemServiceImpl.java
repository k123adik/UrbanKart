package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.OutOfStockException;
import com.example.UrbanKart.exception.ProductNotFoundException;
import com.example.UrbanKart.model.Customer;
import com.example.UrbanKart.model.Item;
import com.example.UrbanKart.model.Product;
import com.example.UrbanKart.repository.CustomerRepository;
import com.example.UrbanKart.repository.ProductRepository;
import com.example.UrbanKart.service.ItemService;
import com.example.UrbanKart.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product dose not exist!");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exist!");
        }

        Product product = productOptional.get();

        if(product.getQuantity() == 0){
            throw new OutOfStockException("The product is out of stock!");
        }
        if(product.getQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        //create item

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());

        return item;
    }
}
