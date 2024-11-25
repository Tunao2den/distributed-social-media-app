package com.tuna.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.entity.Users;
import com.tuna.userservice.service.UserRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users-service")
public class UserController {
    private final UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/test-header")
    public ResponseEntity<String> testHeader(@RequestHeader("userName") String userName) {
        return ResponseEntity.ok(userName);
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody UserRequest userRequest) throws JsonProcessingException {
        return userRegisterService.registerUser(userRequest);
    }
}