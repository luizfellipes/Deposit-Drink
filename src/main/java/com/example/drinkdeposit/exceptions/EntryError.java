package com.example.drinkdeposit.exceptions;

public class EntryError extends RuntimeException {

    public EntryError() {
    }

    public EntryError(String message) {
        super(message);
    }
}
