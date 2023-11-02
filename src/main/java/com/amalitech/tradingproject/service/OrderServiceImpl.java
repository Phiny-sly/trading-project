package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.OrderDto;
import com.amalitech.tradingproject.entity.Order;
import com.amalitech.tradingproject.entity.Product;
import com.amalitech.tradingproject.entity.ProductLine;
import com.amalitech.tradingproject.exception.OrderDoesNotExistException;
import com.amalitech.tradingproject.exception.StockLimitException;
import com.amalitech.tradingproject.payload.OrderPayload;
import com.amalitech.tradingproject.payload.ProductLinePayload;
import com.amalitech.tradingproject.repository.OrderRepository;
import com.amalitech.tradingproject.repository.ProductRepository;
import com.amalitech.tradingproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderDto createOrder(OrderPayload orderPayload, long userId) {
        Order order = new Order();
        List<ProductLine> productLines = new ArrayList<>();
        userRepository.findById(userId).ifPresent(order::setUser);
        orderPayload.getListOfProductLines().forEach(productLine -> validateOrder(productLine, order, productLines));
        order.setListOfProductLines(productLines);
        orderRepository.save(order);
        log.info("order {}", order);
        return EntityMapper.INSTANCE.convertToOrderDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderPayload orderPayload, long id) {
        orderRepository.findById(id).ifPresentOrElse(order -> {
            List<ProductLine> productLines = new ArrayList<>();
            orderPayload.getListOfProductLines().forEach(productLine -> validateOrder(productLine, order, productLines));
            order.setListOfProductLines(productLines);
            orderRepository.save(order);
        }, () -> {
            throw new OrderDoesNotExistException(id);
        });
        return EntityMapper.INSTANCE.convertToOrderDto(orderRepository.findById(id).orElseThrow());
    }

    private void validateOrder(ProductLinePayload productLine, Order order, List<ProductLine> productLines) {
        int orderedQuantity = productLine.getQuantity();
        Product product = productRepository.findById(productLine.getProductId()).orElseThrow();
        int stock = product.getStock();
        if (stock >= orderedQuantity) {
            ProductLine newProductLine = new ProductLine();
            newProductLine.setQuantity(orderedQuantity);
            newProductLine.setProduct(product);
            newProductLine.setOrder(order);
            productLines.add(newProductLine);

            product.setStock(stock - orderedQuantity);
            productRepository.save(product);
        } else {
            throw new StockLimitException(product.getName(), stock, orderedQuantity);
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(EntityMapper.INSTANCE::convertToOrderDto).toList();
    }

    @Override
    public OrderDto getOrderById(long id) {
        return EntityMapper.INSTANCE.convertToOrderDto(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(long userId) {
        return orderRepository.findOrdersByUserId(userId).stream().map(EntityMapper.INSTANCE::convertToOrderDto).toList();
    }
}
