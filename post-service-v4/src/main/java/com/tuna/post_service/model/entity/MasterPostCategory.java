package com.tuna.postservice.model.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    @Column(name = "category_name")
    private String category;
    @OneToMany(mappedBy = "masterPostCategory", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MasterPost> masterPosts;

//    public String getCategory() {
//        return this.category;
//    }

//    public void setCategory(String category) {
//        this.category = category;
//    }
}
