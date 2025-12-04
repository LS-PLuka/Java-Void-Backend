package com.example.demo.exceptions.cartaoExceptions;

public class CartaoNotFoundException extends RuntimeException {

    public CartaoNotFoundException(String message) {
        super(message);
    }
}
