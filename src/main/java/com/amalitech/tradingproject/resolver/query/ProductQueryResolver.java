package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.service.ProductService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public List<ProductDto> getProductsByOrderId(long id) {
        return productService.getProductsByOrderId(id);
    }
}
