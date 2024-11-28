package com.tuna.monolithsocialmediaapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "master_post_category")
@Data
@NoArgsConstructor
public class MasterPostCategory {
    @Id
    @GeneratedValue
    @Column(name = "master_post_category_id")
    private Integer id;
    @Column(name = "category")
    private String category;
    @OneToMany
    @JsonIgnore
    private List<MasterPost> masterPosts;
}
