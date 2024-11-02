package com.tuna.userservice.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
    private String userName;
    private String email;
    private String password;

    public AuthUser() {}
}
