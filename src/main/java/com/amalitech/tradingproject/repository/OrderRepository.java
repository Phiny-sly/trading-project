package com.amalitech.tradingproject.repository;

import com.amalitech.tradingproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    default List<Order> findOrdersByUserId(long userId) {
        return findAll().stream().filter(order -> order.getUser().getId() == userId).toList();
    }
}
