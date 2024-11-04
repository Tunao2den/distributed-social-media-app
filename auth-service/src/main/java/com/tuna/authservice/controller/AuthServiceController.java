package com.tuna.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.authservice.model.AuthUser;
import com.tuna.authservice.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthServiceController {
    private final DatabaseService databaseService;

    public AuthServiceController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/auth/receive-user")
    public ResponseEntity<Boolean> saveUser(@RequestBody String jsonUserData) {
        boolean isSaved = databaseService.saveNewUser(jsonUserData);
        return ResponseEntity.ok(isSaved);
    }
}