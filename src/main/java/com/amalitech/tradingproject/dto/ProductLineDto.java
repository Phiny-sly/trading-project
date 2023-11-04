package com.amalitech.tradingproject.dto;

import lombok.Data;

@Data
public class ProductLineDto {
    private long productId;
    private int quantity;
    private double unitPrice;
    private String productName;
}
