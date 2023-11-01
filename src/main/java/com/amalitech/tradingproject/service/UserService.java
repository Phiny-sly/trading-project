package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.payload.UserPayload;

import java.util.List;

public interface UserService {
    UserDto createUser(UserPayload userPayload);
    UserDto updateUser(long id,UserPayload userPayload);
    void deleteUser(long id);
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
}
