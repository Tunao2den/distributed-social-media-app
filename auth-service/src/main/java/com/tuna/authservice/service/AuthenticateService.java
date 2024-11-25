package com.tuna.authservice.service;

import com.tuna.authservice.payload.request.AuthRequest;
import com.tuna.authservice.payload.response.ValidateTokenResponse;
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

    private boolean validateToken(String token) {
        try {
            jwtService.validateToken(token);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public ValidateTokenResponse validateTokenAndExtractUsername(String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        boolean isValid = validateToken(token);
        String userName = jwtService.getSubjectFromToken(token);
        return new ValidateTokenResponse(isValid, userName);
    }

    // TODO: 13.11.2024 add refresh token
}
