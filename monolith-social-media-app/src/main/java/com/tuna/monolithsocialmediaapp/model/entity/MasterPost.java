package com.tuna.monolithsocialmediaapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_post")
@Getter
@Setter
@NoArgsConstructor
public class MasterPost {
    @Id
    @GeneratedValue
    @Column(name = "master_post_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private MasterPostCategory masterPostCategory;
    @Column(name = "content")
    private String content;
    @Column(name = "current_streak")
    private Integer currentStreak;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
