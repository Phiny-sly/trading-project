package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.exception.OrderDoesNotExistException;
import com.amalitech.tradingproject.exception.ProductDoesNotExistException;
import com.amalitech.tradingproject.exception.StockLimitException;
import com.amalitech.tradingproject.exception.UserDoesNotExistException;
import com.amalitech.tradingproject.model.Order;
import com.amalitech.tradingproject.model.Product;
import com.amalitech.tradingproject.model.ProductLine;
import com.amalitech.tradingproject.model.User;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.payload.ProductLinePayload;
import com.amalitech.tradingproject.repository.OrderRepository;
import com.amalitech.tradingproject.repository.ProductRepository;
import com.amalitech.tradingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for order management operations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    @Override
    @Transactional
    public OrderDto createOrder(OrderPayload orderPayload) {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Creating order for user: {}", email);
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserDoesNotExistException(email));
        
        Order order = new Order();
        order.setUser(user);
        
        List<ProductLine> productLines = new ArrayList<>();
        for (ProductLinePayload productLinePayload : orderPayload.getListOfProductLines()) {
            ProductLine productLine = createProductLine(productLinePayload, order);
            productLines.add(productLine);
        }
        
        order.setListOfProductLines(productLines);
        Order savedOrder = orderRepository.save(order);
        
        log.info("Order created successfully with id: {} for user: {}", savedOrder.getId(), email);
        return EntityMapper.INSTANCE.convertToOrderDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderPayload orderPayload, Long orderId) {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Updating order with id: {} for user: {}", orderId, email);
        
        Order order = orderRepository.findByOrderIdAndUserEmail(orderId, email)
            .orElseThrow(() -> new OrderDoesNotExistException(orderId));
        
        // Remove existing product lines
        order.getListOfProductLines().clear();
        
        // Add new product lines
        List<ProductLine> productLines = new ArrayList<>();
        for (ProductLinePayload productLinePayload : orderPayload.getListOfProductLines()) {
            ProductLine productLine = createProductLine(productLinePayload, order);
            productLines.add(productLine);
        }
        
        order.setListOfProductLines(productLines);
        Order updatedOrder = orderRepository.save(order);
        
        log.info("Order with id {} updated successfully for user: {}", orderId, email);
        return EntityMapper.INSTANCE.convertToOrderDto(updatedOrder);
    }

    /**
     * Creates a ProductLine from the payload and validates stock availability.
     *
     * @param productLinePayload the product line payload
     * @param order the order to associate with
     * @return the created ProductLine
     */
    private ProductLine createProductLine(ProductLinePayload productLinePayload, Order order) {
        Long productId = productLinePayload.getProductId();
        int orderedQuantity = productLinePayload.getQuantity();
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductDoesNotExistException(productId));
        
        int availableStock = product.getStock();
        if (availableStock < orderedQuantity) {
            throw new StockLimitException(product.getName(), availableStock, orderedQuantity);
        }
        
        // Update product stock
        product.setStock(availableStock - orderedQuantity);
        productRepository.save(product);
        
        ProductLine productLine = new ProductLine();
        productLine.setProduct(product);
        productLine.setOrder(order);
        productLine.setQuantity(orderedQuantity);
        
        return productLine;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.debug("Retrieving all orders");
        return orderRepository.findAll().stream()
            .map(EntityMapper.INSTANCE::convertToOrderDto)
            .toList();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Retrieving order with id: {} for user: {}", id, email);
        
        Order order = orderRepository.findByOrderIdAndUserEmail(id, email)
            .orElseThrow(() -> new OrderDoesNotExistException(id));
            
        return EntityMapper.INSTANCE.convertToOrderDto(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Deleting order with id: {} for user: {}", id, email);
        
        Order order = orderRepository.findByOrderIdAndUserEmail(id, email)
            .orElseThrow(() -> new OrderDoesNotExistException(id));
            
        orderRepository.delete(order);
        log.info("Order with id {} deleted successfully for user: {}", id, email);
    }

    @Override
    public List<OrderDto> getOrdersByUserId() {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Retrieving all orders for user: {}", email);
        
        return orderRepository.findByUserEmail(email).stream()
            .map(EntityMapper.INSTANCE::convertToOrderDto)
            .toList();
    }
}
