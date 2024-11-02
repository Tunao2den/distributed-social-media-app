package com.tuna.userservice.model.mapper;

import com.tuna.userservice.model.DTO.UserDTO;
import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.data.User;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper {

    public AuthUserMapper() {}

    public AuthUser toAuthUser(UserDTO userDTO) {
        AuthUser authUser = new AuthUser();

        authUser.setEmail(userDTO.getEmail());
        authUser.setUserName(userDTO.getUserName());
        authUser.setPassword(userDTO.getPassword());

        return authUser;
    }
}
