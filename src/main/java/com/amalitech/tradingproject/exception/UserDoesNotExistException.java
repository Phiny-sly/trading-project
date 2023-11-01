package com.amalitech.tradingproject.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(long id) {
        super(String.format("User with id %d does not exist", id));
    }
}
