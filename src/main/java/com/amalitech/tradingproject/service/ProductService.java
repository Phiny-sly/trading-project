package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.dto.ProductLineDto;
import com.amalitech.tradingproject.payload.ProductPayload;

import java.util.List;

/**
 * Service interface for product management operations.
 */
public interface ProductService {
    
    /**
     * Creates a new product.
     *
     * @param productPayload the product data to create
     * @return the created product DTO
     */
    ProductDto createProduct(ProductPayload productPayload);
    
    /**
     * Updates an existing product.
     *
     * @param productPayload the product data to update
     * @param id the product ID to update
     * @return the updated product DTO
     */
    ProductDto updateProduct(ProductPayload productPayload, Long id);
    
    /**
     * Retrieves all products.
     *
     * @return list of all product DTOs
     */
    List<ProductDto> getAllProducts();
    
    /**
     * Retrieves all product lines for a specific order.
     *
     * @param orderId the order ID
     * @return list of product line DTOs
     */
    List<ProductLineDto> getProductsByOrderId(Long orderId);
    
    /**
     * Deletes a product by ID.
     *
     * @param id the product ID to delete
     */
    void deleteProduct(Long id);
}
