package com.example.UrbanKart.controller;

import com.example.UrbanKart.Enum.Gender;
import com.example.UrbanKart.dto.RequestDto.CustomerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CustomerResponseDto;
import com.example.UrbanKart.model.Customer;
import com.example.UrbanKart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("females_age_between_20_and_30")
    public ResponseEntity getAllFemaleCustomersBetweenAge20And30(){

        List<CustomerResponseDto> customerResponseDtos = customerService.getAllFemaleCustomersBetweenAge20And30();
        return new ResponseEntity(customerResponseDtos, HttpStatus.FOUND);
    }

    @GetMapping("males_age_less_than_45")
    public ResponseEntity getAllMaleCustomersAgeLessThan45(){

        List<CustomerResponseDto> customerResponseDtos = customerService.maleCustomersAgeLessThan45();
        return new ResponseEntity(customerResponseDtos, HttpStatus.FOUND);
    }

    //customers who have atleast k orders
}
