package com.amalitech.tradingproject.controller;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.payload.UserPayload;
import com.amalitech.tradingproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @MutationMapping
    public void deleteUser(@Argument("id") long id) {
        userService.deleteUser(id);
    }

    @MutationMapping
    public UserDto updateUser(@Argument("id") long id, @Argument("input") UserPayload userPayload) {
        return userService.updateUser(id, userPayload);
    }

    @MutationMapping(value = "createUser")
    public UserDto createUser(@Argument("input") UserPayload userPayload) {
        return userService.createUser(userPayload);
    }

    @QueryMapping
    @Secured("ROLE_ADMIN")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public UserDto getUserById(@Argument("id") long id) {
        return userService.getUserById(id);
    }
}

