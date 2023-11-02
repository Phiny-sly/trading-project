package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.service.ProductService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class ProductMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private ProductService productService;

    @MutationMapping
    public void deleteProduct(long id) {
        productService.deleteProduct(id);
    }

    @MutationMapping
    public ProductDto updateProduct(ProductPayload productPayload, long id) {
        return productService.updateProduct(productPayload, id);
    }

    @MutationMapping
    public ProductDto createProduct(ProductPayload productPayload) {
        return productService.createProduct(productPayload);
    }
}
