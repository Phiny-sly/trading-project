package com.amalitech.tradingproject.repository;

import com.amalitech.tradingproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Order entity operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    /**
     * Finds all orders for a user by their email.
     *
     * @param email the user's email
     * @return list of orders for the user
     */
    @Query("SELECT o FROM Order o WHERE o.user.email = :email")
    List<Order> findByUserEmail(@Param("email") String email);

    /**
     * Finds an order by its ID and the user's email.
     *
     * @param email the user's email
     * @param orderId the order ID
     * @return Optional containing the order if found, empty otherwise
     */
    @Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.user.email = :email")
    Optional<Order> findByOrderIdAndUserEmail(@Param("orderId") Long orderId, @Param("email") String email);
}
