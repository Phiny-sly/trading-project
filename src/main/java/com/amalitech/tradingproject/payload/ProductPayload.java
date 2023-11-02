package com.amalitech.tradingproject.payload;

import lombok.Data;

@Data
public class ProductPayload {
    private String name;
    private double price;
    private int stock;
}
