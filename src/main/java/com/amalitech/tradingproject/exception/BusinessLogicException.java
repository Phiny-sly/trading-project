package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when a business logic constraint is violated.
 */
public class BusinessLogicException extends BaseException {
    
    public BusinessLogicException(String message) {
        super(message);
    }
    
    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}

