package com.example.demo.exceptions.userExceptions;

public class ExistsUsernameException extends RuntimeException {

    public ExistsUsernameException(String message) {
        super(message);
    }
}
