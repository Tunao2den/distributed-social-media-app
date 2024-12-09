package com.tuna.monolithsocialmediaapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "daily_post")
@NoArgsConstructor
@Getter
@Setter
public class DailyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_post_id")
    private Integer id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "master_post_id")
    private MasterPost masterPost;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
