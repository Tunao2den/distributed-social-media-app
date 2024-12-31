package com.tuna.usersservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.payload.request.*;
import com.tuna.usersservice.service.UsersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return usersService.loginUser(loginRequest);
    }

    @PostMapping("/follow-user")
    public ResponseEntity<?> follow(@Valid @RequestBody FollowUserRequest followUserRequest) throws JsonProcessingException {
        return usersService.sendFollowRequest(followUserRequest);
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@RequestBody UserInfoRequest userInfoRequest) {
        return usersService.getFollowers(userInfoRequest);
    }

    @GetMapping("/followed")
    public ResponseEntity<?> getFollowed(@RequestParam @Min(1) Integer userId) {
        return usersService.getFollowedUsers(userId);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<?> recommendUsers(@RequestBody UserInfoRequest userInfoRequest) {
        return usersService.recommendUsers(userInfoRequest);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String username) {
        return usersService.searchUsersByUsername(username);
    }

    @PostMapping("/switch-public-account")
    public ResponseEntity<?> switchToPublicAccount(@RequestHeader Integer userId) {
        return usersService.switchToPublicAccount(userId);
    }

    @PostMapping("/switch-private-account")
    public ResponseEntity<?> switchToPrivateAccount(@RequestHeader Integer userId) {
        return usersService.switchToPrivateAccount(userId);
    }

    @GetMapping("follow-requests")
    public ResponseEntity<?> getFollowRequests(@RequestHeader Integer userId) {
        return usersService.getFollowRequests(userId);
    }

    @PostMapping("/accept-follow-request")
    public ResponseEntity<?> acceptFollowRequest(@RequestBody HandleFollowingRequest handleFollowingRequest) {
        return usersService.acceptFollowRequest(handleFollowingRequest);
    }

    @PostMapping("/decline-follow-request")
    public ResponseEntity<?> declineFollowRequest(@RequestBody HandleFollowingRequest handleFollowingRequest) {
        return usersService.declineFollowRequest(handleFollowingRequest);
    }
}