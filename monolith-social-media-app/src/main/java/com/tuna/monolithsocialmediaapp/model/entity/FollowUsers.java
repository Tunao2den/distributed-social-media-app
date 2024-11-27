package com.tuna.monolithsocialmediaapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "follow_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_user_id")
    private int id;
    @Column(name = "follower_id")
    private int followerId;
    @Column(name = "followed_id")
    private int followedId;
}
