package com.amalitech.tradingproject.payload;

import lombok.Data;

import java.util.List;

@Data
public class OrderPayload {
    private List<ProductLinePayload> listOfProductLines;
}
