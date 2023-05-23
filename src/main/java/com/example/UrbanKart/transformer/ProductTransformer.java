package com.example.UrbanKart.transformer;

import com.example.UrbanKart.Enum.ProductStatus;
import com.example.UrbanKart.dto.RequestDto.ProductRequestDto;
import com.example.UrbanKart.dto.ResponseDto.ProductResponseDto;
import com.example.UrbanKart.model.Product;

public class ProductTransformer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
