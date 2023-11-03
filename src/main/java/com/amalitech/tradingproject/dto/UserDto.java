package com.amalitech.tradingproject.dto;

import com.amalitech.tradingproject.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private Role role;
}
