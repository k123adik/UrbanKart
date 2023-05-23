package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
