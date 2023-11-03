package com.amalitech.tradingproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private long id;
    private long userId;
    private String email;
    private List<ProductLineDto> listOfProductLines;
}
