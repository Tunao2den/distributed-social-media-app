package com.tuna.monolithsocialmediaapp.controller;

import com.tuna.monolithsocialmediaapp.model.entity.Users;
import com.tuna.monolithsocialmediaapp.payload.request.UserRegisterRequest;
import com.tuna.monolithsocialmediaapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/users/{userName}")
    public Users getUser(@PathVariable String userName) {
        return usersService.getUserByName(userName);
    }

    @PostMapping("/users")
    public Users registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return usersService.registerUser(userRegisterRequest);
    }
}
