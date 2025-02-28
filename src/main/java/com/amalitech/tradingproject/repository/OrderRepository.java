package com.amalitech.tradingproject.repository;

import com.amalitech.tradingproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    default List<Order> findOrdersByUserId(String email) {
        return findAll().stream().filter(order -> order.getUser().getEmail().equals(email)).toList();
    }

    default Optional<Order> findByUsernameAndOrderId(String email, long id) {
        return findAll().stream()
                .filter(order -> order.getUser().getEmail().equals(email) && order.getId().equals(id))
                .findAny();
    }
}
