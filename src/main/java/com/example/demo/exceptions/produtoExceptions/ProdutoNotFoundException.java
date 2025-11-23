package com.example.demo.exceptions.produtoExceptions;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException (String message) {
        super(message);
    }
}
