package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.payload.ProductPayload;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductPayload productPayload);
    ProductDto updateProduct(ProductPayload productPayload, long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByOrderId(long orderId);
    void deleteProduct(long id);
}
