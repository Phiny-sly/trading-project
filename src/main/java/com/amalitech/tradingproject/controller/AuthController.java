package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.payload.AuthPayload;
import com.amalitech.tradingproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

/**
 * GraphQL controller for authentication operations.
 */
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public AuthDto login(@Argument("input") AuthPayload authPayload) {
        return authService.login(authPayload);
    }
}
