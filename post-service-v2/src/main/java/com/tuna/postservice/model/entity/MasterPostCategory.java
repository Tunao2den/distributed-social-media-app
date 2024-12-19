package com.tuna.postservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "master_post_category")
public class MasterPostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    @Column(name = "category_name")
    private String category;
    @OneToMany(mappedBy = "masterPostCategory", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MasterPost> masterPosts;

    public MasterPostCategory() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<MasterPost> getMasterPosts() {
        return masterPosts;
    }

    public void setMasterPosts(List<MasterPost> masterPosts) {
        this.masterPosts = masterPosts;
    }
}
