package com.tuna.postservice.service;

import com.tuna.postservice.model.entity.MasterPostCategory;
import com.tuna.postservice.payload.response.MessageResponse;
import com.tuna.postservice.repository.MasterPostCategoryRepository;
import com.tuna.postservice.repository.MasterPostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private MasterPostRepository masterPostRepository;

    @Autowired
    private MasterPostCategoryRepository masterPostCategoryRepository;

    @Transactional
    public ResponseEntity<?> createMasterPostCategory(String postCategory) {
        if (masterPostCategoryRepository.existsByCategory(postCategory)) {
            return ResponseEntity.badRequest().body(new MessageResponse("This category already exists"));
        }
        try {
            MasterPostCategory masterPostCategory = new MasterPostCategory();
            masterPostCategory.setCategory(postCategory);
            masterPostCategoryRepository.save(masterPostCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not save the category"));
        }
        return ResponseEntity.ok(new MessageResponse("Category created successfully"));
    }

    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(masterPostCategoryRepository.findAll());
    }

    public List<MasterPostCategory> getAllCategoriesList() {
        return masterPostCategoryRepository.findAll();
    }

    public ResponseEntity<?> findUsersPostsByUserId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findByUserId(id));
    }

    public ResponseEntity<?> findPostByPostId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findPostById(id));
    }
}
