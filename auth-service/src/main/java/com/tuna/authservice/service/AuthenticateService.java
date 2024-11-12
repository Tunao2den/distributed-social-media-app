package com.tuna.authservice.service;

import com.tuna.authservice.payload.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticateUserAndGetToken(AuthRequest authRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    public boolean validateToken(String authHeader) {
        try {
            String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
            jwtService.validateToken(token);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
