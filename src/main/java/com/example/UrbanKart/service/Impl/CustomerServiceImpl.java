package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.dto.RequestDto.CustomerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CustomerResponseDto;
import com.example.UrbanKart.model.Cart;
import com.example.UrbanKart.model.Customer;
import com.example.UrbanKart.repository.CustomerRepository;
import com.example.UrbanKart.service.CustomerService;
import com.example.UrbanKart.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //dto to entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); //will save both customer and cart

        //prepare responseDto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllFemaleCustomersBetweenAge20And30() {

        List<Customer> customers = customerRepository.femaleCustomersAgeBetween20And30();

        //entity to response dto
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Customer customer : customers){
            customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }

        return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto> maleCustomersAgeLessThan45() {

        List<Customer> customers = customerRepository.maleCustomersAgeLessThan45();

        //entity to dto
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Customer customer : customers){
            customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }

        return customerResponseDtos;
    }
}
