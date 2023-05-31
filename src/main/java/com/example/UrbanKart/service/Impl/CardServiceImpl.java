package com.example.UrbanKart.service.Impl;

import com.example.UrbanKart.dto.RequestDto.CardRequestDto;
import com.example.UrbanKart.dto.ResponseDto.CardResponseDto;
import com.example.UrbanKart.exception.CustomerNotFoundException;
import com.example.UrbanKart.model.Card;
import com.example.UrbanKart.model.Customer;
import com.example.UrbanKart.repository.CustomerRepository;
import com.example.UrbanKart.service.CardService;
import com.example.UrbanKart.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        //check customer is present in db
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found. Please check email id!");
        }

        //dto to entity
        Card card = CardTransformer.cardRequestDtoTocard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        //save customer and card
        Customer savedCustomer = customerRepository.save(customer);

        //prepare response dto
        return CardTransformer.cardToCardResponseDto(card);
    }
}
