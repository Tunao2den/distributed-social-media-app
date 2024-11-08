package com.tuna.authservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.authservice.model.entity.AuthUser;
import com.tuna.authservice.repository.AuthServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    private final AuthServiceRepository authServiceRepository;
    private final ObjectMapper objectMapper;

    public DatabaseService(AuthServiceRepository authServiceRepository, ObjectMapper objectMapper) {
        this.authServiceRepository = authServiceRepository;
        this.objectMapper = objectMapper;
    }

    public boolean saveNewUser(String jsonUserData) {
        try {
            AuthUser authUser = objectMapper.readValue(jsonUserData, AuthUser.class);
            // TODO: 9.11.2024 bcrypt password
            authServiceRepository.save(authUser);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}