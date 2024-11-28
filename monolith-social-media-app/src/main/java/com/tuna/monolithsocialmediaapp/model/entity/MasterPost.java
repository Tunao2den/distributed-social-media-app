package com.tuna.monolithsocialmediaapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "master_post")
@Data
@NoArgsConstructor
public class MasterPost {
    @Id
    @GeneratedValue
    @Column(name = "master_post_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private MasterPostCategory masterPostCategory;
    @Column(name = "content")
    private String content;
    @Column(name = "current_streak")
    private Integer currentStreak;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
