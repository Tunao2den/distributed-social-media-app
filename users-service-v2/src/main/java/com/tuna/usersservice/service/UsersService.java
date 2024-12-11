package com.tuna.usersservice.service;

import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public List<Users> getUsers() {
        List<Users> users= usersRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }
        return users;
    }

    public Users getUserByName(String userName) {
        if (!usersRepository.existsByUserName(userName)) {
            throw new RuntimeException("User not found");
        }
        return usersRepository.findByUserName(userName);
    }
}
