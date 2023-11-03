package com.amalitech.tradingproject.payload;

import lombok.Data;

@Data
public class AuthPayload {
    private String email;
    private String password;
}
