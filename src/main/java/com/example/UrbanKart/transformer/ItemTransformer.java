package com.example.UrbanKart.transformer;

import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.dto.ResponseDto.ItemResponseDto;
import com.example.UrbanKart.model.Customer;
import com.example.UrbanKart.model.Item;
import com.example.UrbanKart.model.Product;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(int quantity){

        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponsedto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
