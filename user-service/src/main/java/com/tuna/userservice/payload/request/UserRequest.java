package com.tuna.userservice.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private LocalDateTime birthDate;

    public UserRequest() {}
}