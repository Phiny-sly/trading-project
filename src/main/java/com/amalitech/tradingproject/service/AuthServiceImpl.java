package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.payload.AuthPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for authentication operations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final JwtGeneratorService jwtGeneratorService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = true)
    public AuthDto login(AuthPayload authPayload) {
        log.debug("Attempting login for email: {}", authPayload.getEmail());
        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authPayload.getEmail(), 
                    authPayload.getPassword()
                )
            );
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(authPayload.getEmail());
            String token = jwtGeneratorService.generateToken(userDetails);
            
            log.info("Login successful for email: {}", authPayload.getEmail());
            return new AuthDto(token);
            
        } catch (org.springframework.security.core.AuthenticationException e) {
            log.warn("Login failed for email: {} - Invalid credentials", authPayload.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
