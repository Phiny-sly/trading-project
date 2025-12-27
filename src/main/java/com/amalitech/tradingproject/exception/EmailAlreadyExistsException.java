package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when trying to register a user with an email that already exists.
 */
public class EmailAlreadyExistsException extends ResourceAlreadyExistsException {
    
    public EmailAlreadyExistsException(String email) {
        super(String.format("User with email %s already exists", email));
    }
}
