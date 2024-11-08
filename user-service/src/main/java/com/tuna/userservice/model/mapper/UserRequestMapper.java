package com.tuna.userservice.model.mapper;

import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.entity.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserRequestMapper {
    public Users toUser(UserRequest userRequest) {
        Users users = new Users();
        users.setFirstName(userRequest.getFirstName());
        users.setLastName(userRequest.getLastName());
        users.setEmail(userRequest.getEmail());
        users.setUserName(userRequest.getUserName());
        users.setBirthDate(userRequest.getBirthDate());
        users.setRegistrationDate(LocalDateTime.now());

        return users;
    }
}
