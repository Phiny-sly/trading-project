package com.amalitech.tradingproject.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name) {
        super(String.format("Product with name %s already exists", name));
    }
}
