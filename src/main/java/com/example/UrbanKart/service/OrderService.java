package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.OrderRequestDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.exception.ProductNotFoundException;
import com.example.UrbanKart.model.Card;
import com.example.UrbanKart.model.Cart;
import com.example.UrbanKart.model.OrderEntity;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}
