package com.tuna.authservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.authservice.model.entity.AuthUser;
import com.tuna.authservice.repository.AuthServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    private final AuthServiceRepository authServiceRepository;
    private final ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatabaseService(AuthServiceRepository authServiceRepository, ObjectMapper objectMapper) {
        this.authServiceRepository = authServiceRepository;
        this.objectMapper = objectMapper;
    }

    public boolean saveNewUser(String jsonUserData) {
        try {
            AuthUser authUser = objectMapper.readValue(jsonUserData, AuthUser.class);
            authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
            authServiceRepository.save(authUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}