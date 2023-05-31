package com.example.UrbanKart.controller;

import com.example.UrbanKart.dto.RequestDto.OrderRequestDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.exception.ProductNotFoundException;
import com.example.UrbanKart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws InsufficientQuantityException, CustomerNotFoundException, ProductNotFoundException, InvalidCardException {

        try{
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
