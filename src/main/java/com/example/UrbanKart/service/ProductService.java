package com.example.UrbanKart.service;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.dto.RequestDto.ProductRequestDto;
import com.example.UrbanKart.dto.ResponseDto.ProductResponseDto;
import com.example.UrbanKart.exception.SellerNotFoundException;
import com.example.UrbanKart.model.Product;
import com.example.UrbanKart.model.Seller;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);

    public List<ProductResponseDto> getAllProductsSoldByASellerInACategory(Category category, int sellerId);
}
