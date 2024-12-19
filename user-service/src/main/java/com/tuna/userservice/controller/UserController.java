package com.tuna.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuna.userservice.model.entity.Follow;
import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.entity.Users;
import com.tuna.userservice.repository.FollowRepository;
import com.tuna.userservice.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users-service")
public class UserController {
    private final UserRegisterService userRegisterService;


    private final FollowRepository followRepository;

    public UserController(UserRegisterService userRegisterService, FollowRepository followRepository) {
        this.userRegisterService = userRegisterService;
        this.followRepository = followRepository;
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

    @PostMapping
    public void sendFollowRequest(@RequestBody String follower, @RequestBody String followed) {

        followRepository.save();
    }
}