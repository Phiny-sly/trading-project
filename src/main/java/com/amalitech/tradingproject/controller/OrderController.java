package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @MutationMapping
    public void deleteOrder(@Argument("id") long id) {
        orderService.deleteOrder(id);
    }

    @MutationMapping
    public OrderDto updateOrder(@Argument("input") OrderPayload orderPayload,@Argument("id") long id) {
        return orderService.updateOrder(orderPayload, id);
    }

    @MutationMapping
    public OrderDto createOrder(@Argument("input") OrderPayload orderPayload,@Argument("userId") long userId) {
        return orderService.createOrder(orderPayload, userId);
    }

    @QueryMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @QueryMapping
    public OrderDto getOrderById(@Argument("id") long id) {
        return orderService.getOrderById(id);
    }

    @QueryMapping
    public List<OrderDto> getOrdersByUserId(@Argument("id") long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
