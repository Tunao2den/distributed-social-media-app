package com.tuna.usersservice.service;

import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.payload.request.LoginRequest;
import com.tuna.usersservice.payload.request.RegisterRequest;
import com.tuna.usersservice.payload.response.LoginResponse;
import com.tuna.usersservice.payload.response.MessageResponse;
import com.tuna.usersservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        String firstName = registerRequest.getFirstName();
        String lastName = registerRequest.getLastName();
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        LocalDate birthDate = registerRequest.getBirthDate();
        if (usersRepository.existsByUserName(username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if(usersRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }
        // TODO: 12.12.2024 encode password(spring security)
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        user.setRegisteredAt(LocalDateTime.now());
        usersRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Users user = usersRepository.findUsersByUserNameAndPassword(username, password);
        if (user != null) {
            return ResponseEntity.ok(new LoginResponse(user.getId(), user.getUserName()));
        } else {
            return ResponseEntity.ok(new MessageResponse("Login failed!"));
        }
    }
}
