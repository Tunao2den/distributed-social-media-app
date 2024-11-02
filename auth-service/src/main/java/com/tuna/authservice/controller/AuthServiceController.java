package com.tuna.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.authservice.model.AuthUser;
import com.tuna.authservice.service.DatabaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthServiceController {
    private final ObjectMapper objectMapper;
    private final DatabaseService databaseService;

    public AuthServiceController(ObjectMapper objectMapper, DatabaseService databaseService) {
        this.objectMapper = objectMapper;
        this.databaseService = databaseService;
    }

    @PostMapping("/receive-user")
    public boolean receiveUser(@RequestBody String jsonUserData) throws JsonProcessingException {
        AuthUser authUser = objectMapper.readValue(jsonUserData, AuthUser.class);
        return databaseService.saveNewUser(authUser);
    }
}