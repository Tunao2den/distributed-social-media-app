package com.tuna.authservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AuthUser {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public AuthUser() {}
}
