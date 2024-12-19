package com.tuna.postservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "master_post")
@Getter
@Setter
@NoArgsConstructor
public class MasterPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_post_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private MasterPostCategory masterPostCategory;
    @Column(name = "content")
    private String content;
    @Column(name = "current_streak")
    private Integer currentStreak;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "masterPost", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DailyPost> dailyPosts;
}
