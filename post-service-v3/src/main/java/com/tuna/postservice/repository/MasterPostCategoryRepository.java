package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.MasterPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterPostCategoryRepository extends JpaRepository<MasterPostCategory, Integer> {
    boolean existsByCategory(String category);
}
