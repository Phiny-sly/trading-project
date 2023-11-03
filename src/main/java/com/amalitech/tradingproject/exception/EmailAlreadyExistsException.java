package com.amalitech.tradingproject.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(String.format("%s already exists", email));
    }
}
