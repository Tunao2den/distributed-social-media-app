package com.tuna.usersservice.controller;

import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.payload.request.FollowUserRequest;
import com.tuna.usersservice.payload.request.LoginRequest;
import com.tuna.usersservice.payload.request.RegisterRequest;
import com.tuna.usersservice.payload.request.UserInfoRequest;
import com.tuna.usersservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return ResponseEntity.ok(usersService.getUsers()).getBody();
    }

    @GetMapping("/users/{userName}")
    public Users getUser(@PathVariable String userName) {
        return ResponseEntity.ok(usersService.getUserByName(userName)).getBody();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return usersService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return usersService.loginUser(loginRequest);
    }

    @PostMapping("/follow-user")
    public ResponseEntity<?> follow(@Valid @RequestBody FollowUserRequest followUserRequest) {
        return usersService.sendFollowRequest(followUserRequest);
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@RequestBody UserInfoRequest userInfoRequest) {
        return usersService.getFollowers(userInfoRequest);
    }

    @GetMapping("/followed")
    public ResponseEntity<?> getFollowed(@RequestBody UserInfoRequest userInfoRequest) {
        return usersService.getFollowedUsers(userInfoRequest);
    }
}