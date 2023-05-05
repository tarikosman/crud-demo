package com.example.backend.util.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.util.jpa.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFound(EntityNotFoundException ex) {
        return ex.getMessage();
    }
}