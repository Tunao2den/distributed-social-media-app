package com.tuna.monolithsocialmediaapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "follow_users")
@Getter
@Setter
@NoArgsConstructor
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
