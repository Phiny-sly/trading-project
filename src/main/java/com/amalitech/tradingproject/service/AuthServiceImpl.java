package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.model.User;
import com.amalitech.tradingproject.payload.AuthPayload;
import com.amalitech.tradingproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtGeneratorService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    @Override
    public AuthDto login(AuthPayload authPayload) {
        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authPayload.getEmail(), authPayload.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(authPayload.getEmail());
            token = jwtService.generateToken(userDetails);
            User user = userRepository.findByEmail(authPayload.getEmail()).orElseThrow();
            httpSession.setAttribute("user", user);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }

        return new AuthDto(token);
    }
}
