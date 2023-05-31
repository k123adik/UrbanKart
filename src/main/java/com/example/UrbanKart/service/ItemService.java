package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.OutOfStockException;
import com.example.UrbanKart.exception.ProductNotFoundException;
import com.example.UrbanKart.model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}
