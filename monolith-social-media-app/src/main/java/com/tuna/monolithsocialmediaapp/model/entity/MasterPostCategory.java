package com.tuna.monolithsocialmediaapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "master_post_category")
@Getter
@Setter
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
