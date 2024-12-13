package com.tuna.usersservice.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String firstName;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String lastName;
    @Size(min = 3)
    private String username;
    @Size(max = 50)
    @Email(message = "Not a properly formatted email address")
    private String email;
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;
}
