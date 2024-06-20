package com.example.drinkdeposit.exceptions;

import com.example.drinkdeposit.personalizedResponse.PersonalizedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestsValidation {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> validaCamposNulosOuVazio(MethodArgumentNotValidException exception) {
        Map<String, Object> camposVazios = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(erro -> camposVazios.put(erro.getField(), erro.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PersonalizedResponse(HttpStatus.BAD_REQUEST.value(), camposVazios));
    }

}
