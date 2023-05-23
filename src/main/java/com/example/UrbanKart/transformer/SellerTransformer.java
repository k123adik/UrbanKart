package com.example.UrbanKart.transformer;

import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;
import com.example.UrbanKart.model.Seller;

public class SellerTransformer {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){

        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
}
