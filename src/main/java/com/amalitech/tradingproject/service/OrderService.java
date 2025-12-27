package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;

import java.util.List;

/**
 * Service interface for order management operations.
 */
public interface OrderService {
    
    /**
     * Creates a new order for the authenticated user.
     *
     * @param orderPayload the order data to create
     * @return the created order DTO
     */
    OrderDto createOrder(OrderPayload orderPayload);
    
    /**
     * Updates an existing order.
     *
     * @param orderPayload the order data to update
     * @param orderId the order ID to update
     * @return the updated order DTO
     */
    OrderDto updateOrder(OrderPayload orderPayload, Long orderId);
    
    /**
     * Retrieves all orders (admin only).
     *
     * @return list of all order DTOs
     */
    List<OrderDto> getAllOrders();
    
    /**
     * Retrieves an order by ID for the authenticated user.
     *
     * @param id the order ID
     * @return the order DTO
     */
    OrderDto getOrderById(Long id);
    
    /**
     * Deletes an order by ID.
     *
     * @param id the order ID to delete
     */
    void deleteOrder(Long id);
    
    /**
     * Retrieves all orders for the authenticated user.
     *
     * @return list of order DTOs for the current user
     */
    List<OrderDto> getOrdersByUserId();
}
