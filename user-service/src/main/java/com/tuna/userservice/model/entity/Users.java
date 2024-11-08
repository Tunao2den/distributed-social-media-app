package com.tuna.userservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Social_User")
public class Users {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String firstName;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String lastName;
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String userName;
    @Email(message = "Not a properly formatted email address")
    private String email;
    @Past(message = "Birthdate should be in the past")
    private LocalDateTime birthDate;
    private LocalDateTime registrationDate;

    public Users() {}
}