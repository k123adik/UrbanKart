package com.example.UrbanKart.transformer;

import com.example.UrbanKart.dto.RequestDto.CustomerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CustomerResponseDto;
import com.example.UrbanKart.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .age(customerRequestDto.getAge())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .age(customer.getAge())
                .build();
    }
}
