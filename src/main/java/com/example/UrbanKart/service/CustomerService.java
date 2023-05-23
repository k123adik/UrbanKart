package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.CustomerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
