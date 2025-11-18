package com.example.demo.exceptions;

public class ExistsUsernameException extends RuntimeException {

    public ExistsUsernameException(String message) {
        super(message);
    }
}
