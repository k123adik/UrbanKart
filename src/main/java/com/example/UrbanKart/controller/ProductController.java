package com.example.UrbanKart.controller;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.ProductRequestDto;
import com.example.UrbanKart.dto.ResponseDto.ProductResponseDto;
import com.example.UrbanKart.exception.SellerNotFoundException;
import com.example.UrbanKart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch(SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsByCategoryAndPrice(@PathVariable("category") Category category, @PathVariable("price") int price){

        List<ProductResponseDto> productResponseDtos = productService.getAllProductsByCategoryAndPrice(category, price);
        return new ResponseEntity(productResponseDtos, HttpStatus.ACCEPTED);
    }
}
