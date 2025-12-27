package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.ProductDto;
import com.amalitech.tradingproject.dto.ProductLineDto;
import com.amalitech.tradingproject.exception.OrderDoesNotExistException;
import com.amalitech.tradingproject.exception.ProductAlreadyExistsException;
import com.amalitech.tradingproject.exception.ProductDoesNotExistException;
import com.amalitech.tradingproject.model.Order;
import com.amalitech.tradingproject.model.Product;
import com.amalitech.tradingproject.payload.ProductPayload;
import com.amalitech.tradingproject.repository.OrderRepository;
import com.amalitech.tradingproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for product management operations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public ProductDto createProduct(ProductPayload productPayload) {
        log.debug("Creating product with name: {}", productPayload.getName());
        
        productRepository.findByName(productPayload.getName())
            .ifPresent(product -> {
                throw new ProductAlreadyExistsException(productPayload.getName());
            });
            
        Product product = EntityMapper.INSTANCE.convertToProduct(productPayload);
        Product savedProduct = productRepository.save(product);
        
        log.info("Product created successfully with id: {} and name: {}", savedProduct.getId(), savedProduct.getName());
        return EntityMapper.INSTANCE.convertToProductDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductPayload productPayload, Long id) {
        log.debug("Updating product with id: {}", id);
        
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductDoesNotExistException(id));
            
        EntityMapper.INSTANCE.updateProductDetails(product, productPayload);
        Product updatedProduct = productRepository.save(product);
        
        log.info("Product with id {} updated successfully", id);
        return EntityMapper.INSTANCE.convertToProductDto(updatedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.debug("Retrieving all products");
        return productRepository.findAll().stream()
                .map(EntityMapper.INSTANCE::convertToProductDto)
                .toList();
    }

    @Override
    public List<ProductLineDto> getProductsByOrderId(Long orderId) {
        log.debug("Retrieving products for order with id: {}", orderId);
        
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderDoesNotExistException(orderId));
            
        return order.getListOfProductLines().stream()
                .map(EntityMapper.INSTANCE::convertToProductLineDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.debug("Deleting product with id: {}", id);
        
        if (!productRepository.existsById(id)) {
            throw new ProductDoesNotExistException(id);
        }
        
        productRepository.deleteById(id);
        log.info("Product with id {} deleted successfully", id);
    }
}
