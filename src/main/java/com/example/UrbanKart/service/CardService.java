package com.example.UrbanKart.service;

import com.example.UrbanKart.dto.RequestDto.CardRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CardResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
