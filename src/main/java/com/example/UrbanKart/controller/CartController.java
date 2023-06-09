package com.example.UrbanKart.controller;

import com.example.UrbanKart.dto.RequestDto.CheckoutCartRequestDto;
import com.example.UrbanKart.dto.RequestDto.ItemRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CartResponseDto;
import com.example.UrbanKart.dto.ResponseDto.OrderResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.exception.EmptyCartException;
import com.example.UrbanKart.exception.InsufficientQuantityException;
import com.example.UrbanKart.exception.InvalidCardException;
import com.example.UrbanKart.model.Item;
import com.example.UrbanKart.service.CartService;
import com.example.UrbanKart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item, itemRequestDto);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public  ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws EmptyCartException, InsufficientQuantityException, CustomerNotFoundException, InvalidCardException {

        try{
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
