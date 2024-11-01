package com.tuna.userservice.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public AuthUser() {}

}
