package com.amalitech.tradingproject.resolver;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.payload.UserPayload;
import com.amalitech.tradingproject.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @MutationMapping
    public void deleteUser(@Argument long id) {
        userService.deleteUser(id);
    }

    @MutationMapping
    public UserDto updateUser(@Argument long id, @Argument UserPayload userPayload) {
        return userService.updateUser(id, userPayload);
    }

    @MutationMapping
    public UserDto createUser(@Argument UserPayload userPayload) {
        return userService.createUser(userPayload);
    }
}

