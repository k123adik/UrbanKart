package com.example.UrbanKart.exception;

import com.example.UrbanKart.model.Customer;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String message){

        super(message);
    }
}
