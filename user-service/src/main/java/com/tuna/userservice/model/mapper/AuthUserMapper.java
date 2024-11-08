package com.tuna.userservice.model.mapper;

import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.data.AuthUser;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper {

    public AuthUserMapper() {}

    public AuthUser toAuthUser(UserRequest userRequest) {
        AuthUser authUser = new AuthUser();

        authUser.setEmail(userRequest.getEmail());
        authUser.setUserName(userRequest.getUserName());
        authUser.setPassword(userRequest.getPassword());

        return authUser;
    }
}
