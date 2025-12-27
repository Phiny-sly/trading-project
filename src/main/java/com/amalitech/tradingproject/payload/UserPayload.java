package com.amalitech.tradingproject.payload;

import com.amalitech.tradingproject.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Payload for user creation and update operations.
 */
@Data
public class UserPayload {
    
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Email(message = "Email must be valid")
    private String email;
    
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    private Role role;
}
