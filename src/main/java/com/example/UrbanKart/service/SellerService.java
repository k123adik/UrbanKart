package com.example.UrbanKart.service;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    public List<SellerResponseDto> allSellersOfParticularCategory(Category category);

    public List<SellerResponseDto> sellerWithHighestNoOfProducts();

    public List<SellerResponseDto> sellerWithLowestNoOfProducts();

    public List<SellerResponseDto> sellerSellingCostliestProduct();

    public List<SellerResponseDto> sellerSellingCheapestProduct();
}
