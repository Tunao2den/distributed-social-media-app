package com.tuna.userservice.model.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
@Getter
@Setter
@Entity
@Table(name = "Social_User")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private Date registrationDate;
    @ElementCollection
    private ArrayList<Integer> posts;
    @ElementCollection
    private ArrayList<Integer> savedPosts;
    @ElementCollection
    private ArrayList<Integer> likes;
    @ElementCollection
    private ArrayList<Integer> followers;
    @ElementCollection
    private ArrayList<Integer> following;



    public User() {}

    public User(String firstName, String lastName, String email, String password, Date birthDate, Date registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
    }
}
