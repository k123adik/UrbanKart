package com.example.UrbanKart.dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {

    String emailId;

    String cardNo;

    int cvv;
}
