package com.example.UrbanKart.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {

    String emailId;

    int productId;

    Date orderDate;

    String cardNo;

    int cvv;

    int requiredQuantity;
}
