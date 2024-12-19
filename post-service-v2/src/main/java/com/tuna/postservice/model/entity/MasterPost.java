package com.tuna.postservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "master_post")
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

    public MasterPost(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MasterPostCategory getMasterPostCategory() {
        return masterPostCategory;
    }

    public void setMasterPostCategory(MasterPostCategory masterPostCategory) {
        this.masterPostCategory = masterPostCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<DailyPost> getDailyPosts() {
        return dailyPosts;
    }

    public void setDailyPosts(List<DailyPost> dailyPosts) {
        this.dailyPosts = dailyPosts;
    }
}
