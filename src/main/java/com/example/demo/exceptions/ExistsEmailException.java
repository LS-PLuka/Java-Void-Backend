package com.example.demo.exceptions;

public class ExistsEmailException extends RuntimeException {

    public ExistsEmailException(String message) {
        super(message);
    }
}
