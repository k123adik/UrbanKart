package com.example.UrbanKart.controller;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;
import com.example.UrbanKart.service.SellerService;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/particular_category")
    public ResponseEntity allSellersOfParticularCategory(@RequestParam Category category){

        List<SellerResponseDto> sellerResponseDtos = sellerService.allSellersOfParticularCategory(category);
        return new ResponseEntity(sellerResponseDtos, HttpStatus.FOUND);
    }

    @GetMapping("/highest_no_of_products")
    public ResponseEntity sellerWithHighestNoOfProducts(){

        List<SellerResponseDto> sellerResponseDtos = sellerService.sellerWithHighestNoOfProducts();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.FOUND);
    }

    @GetMapping("/lowest_no_of_products")
    public ResponseEntity sellerWithLowestNoOfProducts() {

        List<SellerResponseDto> sellerResponseDtos = sellerService.sellerWithLowestNoOfProducts();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.FOUND);
    }

    @GetMapping("/selling_costliest_product")
    public ResponseEntity sellerSellingCostliestProduct(){

        List<SellerResponseDto> sellerResponseDtos = sellerService.sellerSellingCostliestProduct();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.FOUND);
    }

    @GetMapping("/selling_cheapest_product")
    public ResponseEntity sellerSellingCheapestProduct(){

        List<SellerResponseDto> sellerResponseDtos = sellerService.sellerSellingCheapestProduct();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.FOUND);
    }
    //update seller info based on email
}
