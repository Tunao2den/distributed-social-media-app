package com.tuna.monolithsocialmediaapp.service;

import com.tuna.monolithsocialmediaapp.model.entity.MasterPostCategory;
import com.tuna.monolithsocialmediaapp.repository.MasterPostCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private MasterPostCategoryRepository masterPostCategoryRepository;

    public List<MasterPostCategory> getAllCategories() {
        return masterPostCategoryRepository.findAll();
    }

    @Transactional
    public MasterPostCategory addNewCategory(String category) {
        if (masterPostCategoryRepository.existsByCategory(category)) {
            throw new RuntimeException(category + " category already exists");
        }
        if (category.isEmpty() || category.isBlank()) {
            throw new RuntimeException("Category name can not be null: " + category);
        }
        MasterPostCategory masterPostCategory = new MasterPostCategory();
        masterPostCategory.setCategory(category);
        return masterPostCategoryRepository.save(masterPostCategory);
    }
}
