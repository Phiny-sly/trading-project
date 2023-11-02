package com.amalitech.tradingproject.payload;

import lombok.Data;

import java.util.Set;

@Data
public class OrderPayload {
    private Set<ProductLinePayload> listOfProductLines;
}
