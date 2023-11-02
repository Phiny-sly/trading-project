package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.service.OrderService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private OrderService orderService;

    @QueryMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @QueryMapping
    public OrderDto getOrderById(long id) {
        return orderService.getOrderById(id);
    }

    @QueryMapping
    public List<OrderDto> getOrdersByUserId(long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
