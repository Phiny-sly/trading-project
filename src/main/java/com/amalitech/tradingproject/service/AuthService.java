package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.payload.AuthPayload;

/**
 * Service interface for authentication operations.
 */
public interface AuthService {
    
    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param authPayload the authentication credentials (email and password)
     * @return the authentication DTO containing the JWT token
     */
    AuthDto login(AuthPayload authPayload);
}
