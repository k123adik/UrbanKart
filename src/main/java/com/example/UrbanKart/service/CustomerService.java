package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.CustomerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CustomerResponseDto;
import com.example.UrbanKart.model.Customer;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    public List<CustomerResponseDto> getAllFemaleCustomersBetweenAge20And30();

    public List<CustomerResponseDto> maleCustomersAgeLessThan45();
}
