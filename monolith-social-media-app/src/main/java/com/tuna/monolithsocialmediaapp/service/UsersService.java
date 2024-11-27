package com.tuna.monolithsocialmediaapp.service;

import com.tuna.monolithsocialmediaapp.model.entity.Users;
import com.tuna.monolithsocialmediaapp.payload.request.UserRegisterRequest;
import com.tuna.monolithsocialmediaapp.repository.UsersRepository;
import jakarta.transaction.Transactional;
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
        System.out.println(users);

        return users;
    }

    public Users getUserByName(String userName) {
        if (!usersRepository.existsByUserName(userName)) {
            throw new RuntimeException("User not found");
        }
        return usersRepository.findByUserName(userName);
    }

    @Transactional
    public Users registerUser(UserRegisterRequest userRegisterRequest) {
        if (usersRepository.existsByUserName(userRegisterRequest.getUserName())) {
            throw new RuntimeException("This username is already taken");
        }

        if (usersRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("This email is already taken");
        }
        Users users = userRegisterRequest.toUser();
        return usersRepository.save(users);
    }
}
