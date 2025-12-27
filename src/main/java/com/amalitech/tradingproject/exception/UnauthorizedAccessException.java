package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when a user attempts to access or modify a resource they don't have permission for.
 */
public class UnauthorizedAccessException extends BusinessLogicException {
    
    public UnauthorizedAccessException(String message) {
        super(message);
    }
    
    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

