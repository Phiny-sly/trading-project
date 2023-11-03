package com.amalitech.tradingproject.dto;

import lombok.Data;

@Data
public class ProductDto {
    private long id;
    private String productName;
    private double price;
    private int stock;
}
