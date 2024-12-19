package com.tuna.postservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "master_post_id")
    private MasterPost masterPost;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "dailyPost", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DailyPostComment> comments;
}
