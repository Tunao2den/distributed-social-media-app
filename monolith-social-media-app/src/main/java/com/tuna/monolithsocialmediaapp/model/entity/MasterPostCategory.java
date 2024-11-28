package com.tuna.monolithsocialmediaapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "master_post_category")
@Data
@NoArgsConstructor
public class MasterPostCategory {
    @Id
    @GeneratedValue
    @Column(name = "master_post_category_id")
    private int id;
    @Column(name = "category")
    private String category;
}
