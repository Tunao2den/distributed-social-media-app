package com.tuna.authservice.controller;

import com.tuna.authservice.payload.request.AuthRequest;
import com.tuna.authservice.service.AuthenticateService;
import com.tuna.authservice.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth-service")
public class AuthServiceController {
    private final DatabaseService databaseService;
    private final AuthenticateService authenticateService;

    public AuthServiceController(DatabaseService databaseService, AuthenticateService authenticateService) {
        this.databaseService = databaseService;
        this.authenticateService = authenticateService;
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/receive-user")
    public ResponseEntity<Boolean> saveUser(@RequestBody String jsonUserData) {
        return ResponseEntity.ok(databaseService.saveNewUser(jsonUserData));
    }

    @PostMapping("/generate-token")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        return authenticateService.authenticateUserAndGetToken(authRequest);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authenticateService.validateToken(token));
    }
}