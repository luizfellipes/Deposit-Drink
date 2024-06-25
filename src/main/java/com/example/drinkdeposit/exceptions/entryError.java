package com.example.drinkdeposit.exceptions;

public class entryError extends RuntimeException {

    public entryError() {
    }

    public entryError(String message) {
        super(message);
    }
}
