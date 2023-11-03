package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.payload.AuthPayload;
import com.amalitech.tradingproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @MutationMapping()
    @PreAuthorize("isAnonymous()")
    public AuthDto login(@Argument("input") AuthPayload authPayload) {
        return authService.login(authPayload);
    }
}
