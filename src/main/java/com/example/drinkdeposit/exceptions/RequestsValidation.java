package com.example.drinkdeposit.exceptions;

import com.example.drinkdeposit.controller.DrinkHistoryController;
import com.example.drinkdeposit.personalizedResponse.PersonalizedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestsValidation {

    private static final Logger log = LoggerFactory.getLogger(RequestsValidation.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> validaCamposNulosOuVazio(MethodArgumentNotValidException exception) {
        Map<String, Object> camposVazios = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(erro -> camposVazios.put(erro.getField(), erro.getDefaultMessage()));
        log.info("error on {}", camposVazios);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PersonalizedResponse(HttpStatus.BAD_REQUEST.value(), camposVazios));
    }

    @ExceptionHandler(IlegalRequest.class)
    public ResponseEntity<Object> status400(IlegalRequest exception) {
        log.error("Error in the request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PersonalizedResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(EntryError.class)
    public ResponseEntity<Object> status500(EntryError exception) {
        log.error("Error internal in the server");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PersonalizedResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

}
