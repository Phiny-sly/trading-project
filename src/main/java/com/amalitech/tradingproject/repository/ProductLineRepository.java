package com.amalitech.tradingproject.repository;

import com.amalitech.tradingproject.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
}
