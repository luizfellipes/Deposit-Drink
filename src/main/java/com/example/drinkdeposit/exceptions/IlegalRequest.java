package com.example.drinkdeposit.exceptions;

public class IlegalRequest extends IllegalArgumentException {

    public IlegalRequest() {
    }

    public IlegalRequest(String message) {
        super(message);
    }
}
