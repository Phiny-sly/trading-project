package com.amalitech.tradingproject.common;

/**
 * Application-wide constants.
 */
public final class Constants {
    
    private Constants() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int BEARER_PREFIX_LENGTH = 7;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    public static final long JWT_TOKEN_EXPIRATION_HOURS = 2L;
    
    public static final String ROLE_PREFIX = "ROLE_";
}

