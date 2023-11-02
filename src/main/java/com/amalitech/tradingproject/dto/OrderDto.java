package com.amalitech.tradingproject.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {
    private long id;
    private long userId;
    private Set<ProductLineDto> listOfProductLines;
}
