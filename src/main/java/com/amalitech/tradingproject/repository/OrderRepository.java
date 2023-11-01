package com.amalitech.tradingproject.repository;

import com.amalitech.tradingproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Long, Order> {
}
