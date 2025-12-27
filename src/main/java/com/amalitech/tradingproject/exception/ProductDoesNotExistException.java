package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when a product is not found.
 */
public class ProductDoesNotExistException extends ResourceNotFoundException {
    
    public ProductDoesNotExistException(Long id) {
        super(String.format("Product with id %d does not exist", id));
    }
}
