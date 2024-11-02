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

    public boolean saveNewUser(AuthUser authUser) {
        try {
            authServiceRepository.save(authUser);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}