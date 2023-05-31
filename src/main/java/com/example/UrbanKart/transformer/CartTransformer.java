package com.example.UrbanKart.transformer;

import com.example.UrbanKart.dto.ResponseDto.CardResponseDto;
import com.example.UrbanKart.dto.ResponseDto.CartResponseDto;
import com.example.UrbanKart.dto.ResponseDto.ItemResponseDto;
import com.example.UrbanKart.model.Cart;
import com.example.UrbanKart.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponsedto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}
