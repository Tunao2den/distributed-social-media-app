package com.tuna.monolithsocialmediaapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FollowUsers> followings;
    @OneToMany(mappedBy = "followed", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FollowUsers> followers;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MasterPost> masterPosts;
}
