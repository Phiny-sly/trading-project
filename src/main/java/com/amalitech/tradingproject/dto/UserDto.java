package com.amalitech.tradingproject.dto;

import com.amalitech.tradingproject.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private Role role;
}
