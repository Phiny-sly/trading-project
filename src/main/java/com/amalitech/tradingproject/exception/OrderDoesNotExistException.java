package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when an order is not found.
 */
public class OrderDoesNotExistException extends ResourceNotFoundException {
    
    public OrderDoesNotExistException(Long id) {
        super(String.format("Order with id %d does not exist", id));
    }
}
