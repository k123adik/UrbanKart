package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.CheckoutCartRequestDto;
import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CartResponseDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.EmptyCartException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}
