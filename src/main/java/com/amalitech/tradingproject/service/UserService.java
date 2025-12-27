package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.payload.UserPayload;

import java.util.List;

/**
 * Service interface for user management operations.
 */
public interface UserService {
    
    /**
     * Creates a new user.
     *
     * @param userPayload the user data to create
     * @return the created user DTO
     */
    UserDto createUser(UserPayload userPayload);
    
    /**
     * Updates an existing user (current authenticated user).
     *
     * @param userPayload the user data to update
     * @return the updated user DTO
     */
    UserDto updateUser(UserPayload userPayload);
    
    /**
     * Deletes the currently authenticated user's account.
     */
    void deleteUser();
    
    /**
     * Retrieves all users.
     *
     * @return list of all user DTOs
     */
    List<UserDto> getAllUsers();
    
    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return the user DTO
     */
    UserDto getUserById(Long id);
    
    /**
     * Retrieves a user by email.
     *
     * @param email the user email
     * @return the user DTO
     */
    UserDto getUserByEmail(String email);
}
