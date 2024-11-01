package com.tuna.userservice.model.mapper;

import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.data.User;


public class AuthUserMapper {

    public AuthUserMapper() {}

    public AuthUser toAuthUser(User user) {
        AuthUser authUser = new AuthUser();

        authUser.setId(user.getId());
        authUser.setFirstName(user.getFirstName());
        authUser.setLastName(user.getLastName());
        authUser.setEmail(user.getEmail());
        authUser.setPassword(user.getPassword());

        return authUser;
    }
}
