package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.model.Order;
import com.amalitech.tradingproject.model.Product;
import com.amalitech.tradingproject.exception.OrderDoesNotExistException;
import com.amalitech.tradingproject.exception.ProductAlreadyExistsException;
import com.amalitech.tradingproject.exception.ProductDoesNotExistException;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.repository.OrderRepository;
import com.amalitech.tradingproject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ProductDto createProduct(ProductPayload productPayload) {
        productRepository.findByName(productPayload.getName()).ifPresent(product -> {
            throw new ProductAlreadyExistsException(productPayload.getName());
        });
        Product product = EntityMapper.INSTANCE.convertToProduct(productPayload);
        productRepository.save(product);
        log.info("Product created successfully");
        return EntityMapper.INSTANCE.convertToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductPayload productPayload, long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductDoesNotExistException(id));
        EntityMapper.INSTANCE.updateProductDetails(product, productPayload);
        productRepository.save(product);
        log.info("Product with id {} updated successfully", id);
        return EntityMapper.INSTANCE.convertToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(EntityMapper.INSTANCE::convertToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByOrderId(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderDoesNotExistException(orderId));
        return order.getListOfProductLines()
                .stream()
                .map(productLine -> EntityMapper.INSTANCE.convertToProductDto(productLine.getProduct()))
                .toList();
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
