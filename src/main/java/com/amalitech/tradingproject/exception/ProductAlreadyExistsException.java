package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when trying to create a product with a name that already exists.
 */
public class ProductAlreadyExistsException extends ResourceAlreadyExistsException {
    
    public ProductAlreadyExistsException(String name) {
        super(String.format("Product with name %s already exists", name));
    }
}
