package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.dto.RequestDto.SellerRequestDto;
import com.example.UrbanKart.dto.ResponseDto.SellerResponseDto;
import com.example.UrbanKart.model.Seller;
import com.example.UrbanKart.repository.SellerRepository;
import com.example.UrbanKart.service.SellerService;
import com.example.UrbanKart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        //dto to entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);

        //save to db
        Seller savedSeller = sellerRepository.save(seller);

        //entity to responseDto
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }
}
