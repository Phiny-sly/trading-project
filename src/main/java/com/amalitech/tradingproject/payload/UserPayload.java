package com.amalitech.tradingproject.payload;

import com.amalitech.tradingproject.model.Role;
import lombok.Data;

@Data
public class UserPayload {
    private String name;
    private String email;
    private String password;
    private Role role;
}
