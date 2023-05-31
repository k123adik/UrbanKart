package com.example.UrbanKart.transformer;

import com.example.UrbanKart.dto.RequestDto.CardRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CardResponseDto;
import com.example.UrbanKart.model.Card;

public class CardTransformer {

    public static Card cardRequestDtoTocard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
