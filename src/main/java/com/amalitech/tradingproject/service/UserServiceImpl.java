package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.exception.EmailAlreadyExistsException;
import com.amalitech.tradingproject.exception.UserDoesNotExistException;
import com.amalitech.tradingproject.model.User;
import com.amalitech.tradingproject.payload.UserPayload;
import com.amalitech.tradingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for user management operations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextService securityContextService;

    @Override
    @Transactional
    public UserDto createUser(UserPayload userPayload) {
        log.debug("Creating user with email: {}", userPayload.getEmail());
        
        userRepository.findByEmail(userPayload.getEmail())
            .ifPresent(user -> {
                throw new EmailAlreadyExistsException(userPayload.getEmail());
            });
            
        User user = EntityMapper.INSTANCE.convertToUser(userPayload);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        
        log.info("User created successfully with email: {}", savedUser.getEmail());
        return EntityMapper.INSTANCE.convertToUserDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserPayload userPayload) {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Updating user with email: {}", email);
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserDoesNotExistException(email));
            
        EntityMapper.INSTANCE.updateUserDetails(user, userPayload);
        
        // Only encode password if it's being updated
        if (userPayload.getPassword() != null && !userPayload.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userPayload.getPassword()));
        }
        
        User updatedUser = userRepository.save(user);
        log.info("User with email {} updated successfully", email);
        return EntityMapper.INSTANCE.convertToUserDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser() {
        String email = securityContextService.getCurrentUserEmail();
        log.debug("Deleting user with email: {}", email);
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserDoesNotExistException(email));
            
        userRepository.delete(user);
        log.info("User with email {} deleted successfully", email);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.debug("Retrieving all users");
        return userRepository.findAll().stream()
            .map(EntityMapper.INSTANCE::convertToUserDto)
            .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        log.debug("Retrieving user with id: {}", id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserDoesNotExistException(id));
        return EntityMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.debug("Retrieving user with email: {}", email);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserDoesNotExistException(email));
        return EntityMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Loading user by username (email): {}", email);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserDoesNotExistException(email));
        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
