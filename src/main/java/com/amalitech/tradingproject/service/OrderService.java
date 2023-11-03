package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.payload.OrderPayload;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderPayload orderPayload,long userId);
    OrderDto updateOrder(OrderPayload orderPayload, long id);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(long id);
    void deleteOrder(long id);
    List<OrderDto> getOrdersByUserId(long userId);
}
