package com.tuna.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuna.userservice.model.DTO.UserDTO;
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
    public void registerUser(@RequestBody UserDTO userDTO) throws JsonProcessingException {
        userRegisterService.registerUser(userDTO);
    }
}