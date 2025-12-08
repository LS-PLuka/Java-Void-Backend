package com.example.demo.exceptions.sacolaExceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class SacolaExceptionHandler {

    @ExceptionHandler(SacolaNotFoundException.class)
    public ResponseEntity<Object> handleSacolaNotFoundException(SacolaNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(404).body(body);
    }
}
