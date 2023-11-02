package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @MutationMapping
    public void deleteProduct(@Argument("id") long id) {
        productService.deleteProduct(id);
    }

    @MutationMapping
    public ProductDto updateProduct(@Argument("input") ProductPayload productPayload, @Argument long id) {
        return productService.updateProduct(productPayload, id);
    }

    @MutationMapping
    public ProductDto createProduct(@Argument("input") ProductPayload productPayload) {
        log.info("Creating product: {}", productPayload);
        return productService.createProduct(productPayload);
    }

    @QueryMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public List<ProductDto> getProductsByOrderId(@Argument("id") long id) {
        return productService.getProductsByOrderId(id);
    }
}
