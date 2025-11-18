package com.example.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ExistsUsernameException.class)
    public ResponseEntity<String> handleUser(ExistsUsernameException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<String> handleEmail(ExistsEmailException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserId(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
