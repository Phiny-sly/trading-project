package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.service.OrderService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class OrderMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private OrderService orderService;

    @MutationMapping
    public void deleteOrder(long id) {
        orderService.deleteOrder(id);
    }

    @MutationMapping
    public OrderDto updateOrder(OrderPayload orderPayload, long id) {
        return orderService.updateOrder(orderPayload, id);
    }

    @MutationMapping
    public OrderDto createOrder(OrderPayload orderPayload, long userId) {
        return orderService.createOrder(orderPayload, userId);
    }
}
