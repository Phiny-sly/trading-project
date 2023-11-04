package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.dto.ProductLineDto;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public void deleteProduct(@Argument("id") long id) {
        productService.deleteProduct(id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public ProductDto updateProduct(@Argument("input") ProductPayload productPayload, @Argument long id) {
        return productService.updateProduct(productPayload, id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public ProductDto createProduct(@Argument("input") ProductPayload productPayload) {
        return productService.createProduct(productPayload);
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<ProductLineDto> getProductsByOrderId(@Argument("id") long id) {
        return productService.getProductsByOrderId(id);
    }
}
