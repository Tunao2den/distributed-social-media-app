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
    private Integer id;
    @JoinColumn(name = "follower_id")
    @ManyToOne
    private Users follower;
    @JoinColumn(name = "followed_id")
    @ManyToOne
    private Users followed;
}
