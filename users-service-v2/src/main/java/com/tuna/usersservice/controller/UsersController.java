package com.tuna.usersservice.controller;

import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.service.UsersService;
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
}