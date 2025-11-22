package com.example.demo.exceptions.marcaExceptions;

public class MarcaNotFoundException extends RuntimeException {

    public MarcaNotFoundException(String message) {
        super(message);
    }
}
