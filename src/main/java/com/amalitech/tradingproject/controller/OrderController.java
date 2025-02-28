package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public void deleteOrder(@Argument("id") long id) {
        orderService.deleteOrder(id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public OrderDto updateOrder(@Argument("input") OrderPayload orderPayload, @Argument("id") long id) {
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
    public OrderDto getOrderById(@Argument("id") long id) {
        return orderService.getOrderById(id);
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<OrderDto> getOrdersByUserId() {
        return orderService.getOrdersByUserId();
    }
}
