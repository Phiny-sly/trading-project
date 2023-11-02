package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.service.UserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public UserDto getUserById(@Argument long id) {
        return userService.getUserById(id);
    }
}
