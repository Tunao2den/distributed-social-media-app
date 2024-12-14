package com.tuna.postservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "daily_post_comment")
@Getter
@Setter
@NoArgsConstructor
public class DailyPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_post_comment_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "daily_post_id")
    private DailyPost dailyPost;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
