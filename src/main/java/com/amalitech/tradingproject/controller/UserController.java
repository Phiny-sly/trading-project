package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.payload.UserPayload;
import com.amalitech.tradingproject.service.SecurityContextService;
import com.amalitech.tradingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * GraphQL controller for user management operations.
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final SecurityContextService securityContextService;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Long deleteUser() {
        // Get the user ID before deletion for return value
        String email = securityContextService.getCurrentUserEmail();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();
        userService.deleteUser();
        return userId;
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public UserDto updateUser(@Argument("input") UserPayload userPayload) {
        return userService.updateUser(userPayload);
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public UserDto createUser(@Argument("input") UserPayload userPayload) {
        return userService.createUser(userPayload);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public UserDto getUserById(@Argument("id") Long id) {
        return userService.getUserById(id);
    }
}
