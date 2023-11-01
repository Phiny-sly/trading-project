package com.amalitech.tradingproject.payload;

import lombok.Data;

@Data
public class CreateUserPayload {
    private String name;
    private String email;
    private String password;
}
