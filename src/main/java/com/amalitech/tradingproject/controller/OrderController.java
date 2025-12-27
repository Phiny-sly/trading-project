package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * GraphQL controller for order management operations.
 */
@Controller
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public void deleteOrder(@Argument("id") Long id) {
        orderService.deleteOrder(id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public OrderDto updateOrder(@Argument("input") OrderPayload orderPayload, @Argument("id") Long id) {
        return orderService.updateOrder(orderPayload, id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public OrderDto createOrder(@Argument("input") OrderPayload orderPayload) {
        return orderService.createOrder(orderPayload);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public OrderDto getOrderById(@Argument("id") Long id) {
        return orderService.getOrderById(id);
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<OrderDto> getOrdersByUserId() {
        return orderService.getOrdersByUserId();
    }
}
