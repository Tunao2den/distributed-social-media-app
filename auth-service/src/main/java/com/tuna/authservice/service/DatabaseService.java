package com.tuna.authservice.service;

import com.tuna.authservice.model.AuthUser;
import com.tuna.authservice.repository.AuthServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    AuthServiceRepository authServiceRepository;

    public DatabaseService(AuthServiceRepository authServiceRepository) {
        this.authServiceRepository = authServiceRepository;
    }

    public void saveNewUser(AuthUser authUser) {
        authServiceRepository.save(authUser);
    }
}
