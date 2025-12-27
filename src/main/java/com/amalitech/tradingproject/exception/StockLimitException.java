package com.amalitech.tradingproject.exception;

/**
 * Exception thrown when trying to order more items than available in stock.
 */
public class StockLimitException extends BusinessLogicException {
    
    public StockLimitException(String productName, int stock, int orderedQuantity) {
        super(String.format("Product %s has only %d items in stock, but %d items were ordered", 
            productName, stock, orderedQuantity));
    }
}
