package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.config.EntityMapper;
import com.amalitech.tradingproject.dto.UserDto;
import com.amalitech.tradingproject.entity.User;
import com.amalitech.tradingproject.exception.EmailAlreadyExistsException;
import com.amalitech.tradingproject.exception.UserDoesNotExistException;
import com.amalitech.tradingproject.payload.UserPayload;
import com.amalitech.tradingproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserPayload userPayload) {
        userRepository.findByEmail(userPayload.getEmail()).ifPresent(user -> {
            throw new EmailAlreadyExistsException(userPayload.getEmail());
        });
        User user = EntityMapper.INSTANCE.convertToUser(userPayload);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return EntityMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    public UserDto updateUser(long id, UserPayload userPayload) {
        var ref = new Object() {
            User userResult = null;
        };
        userRepository.findById(id).ifPresentOrElse(user -> {
            EntityMapper.INSTANCE.updateUserDetails(user, userPayload);
            userRepository.save(user);
            ref.userResult = user;
        }, () -> {
            throw new UserDoesNotExistException(id);
        });

        return EntityMapper.INSTANCE.convertToUserDto(ref.userResult);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(EntityMapper.INSTANCE::convertToUserDto).toList();
    }

    @Override
    public UserDto getUserById(long id) {
        return EntityMapper.INSTANCE.convertToUserDto(userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException(id)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
