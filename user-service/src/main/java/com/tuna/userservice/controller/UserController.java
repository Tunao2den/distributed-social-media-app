package com.tuna.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.entity.Users;
import com.tuna.userservice.service.UserRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody UserRequest userRequest) throws JsonProcessingException {
        return userRegisterService.registerUser(userRequest);
    }
}