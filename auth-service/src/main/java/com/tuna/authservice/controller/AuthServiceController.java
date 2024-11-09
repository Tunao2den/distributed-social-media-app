package com.tuna.authservice.controller;

import com.tuna.authservice.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth-service")
public class AuthServiceController {
    private final DatabaseService databaseService;

    public AuthServiceController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/receive-user")
    public ResponseEntity<Boolean> saveUser(@RequestBody String jsonUserData) {
        boolean isSaved = databaseService.saveNewUser(jsonUserData);
        return ResponseEntity.ok(isSaved);
    }
}