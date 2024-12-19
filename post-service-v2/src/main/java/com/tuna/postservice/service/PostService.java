package com.tuna.postservice.service;

import com.tuna.postservice.model.entity.MasterPost;
import com.tuna.postservice.model.entity.MasterPostCategory;
import com.tuna.postservice.payload.request.CreateMasterPostRequest;
import com.tuna.postservice.payload.response.MessageResponse;
import com.tuna.postservice.repository.MasterPostCategoryRepository;
import com.tuna.postservice.repository.MasterPostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Transactional
    public ResponseEntity<?> createMasterPostToUser(CreateMasterPostRequest createMasterPostRequest) {
        Integer userId = createMasterPostRequest.getUserId();
        Integer masterPostCategoryId = createMasterPostRequest.getMasterPostCategoryId();
        if (!masterPostCategoryRepository.existsById(masterPostCategoryId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Category does not exists"));
        }
        try {
            MasterPostCategory postCategory = masterPostCategoryRepository.getReferenceById(masterPostCategoryId);
            MasterPost masterPost = new MasterPost();
            masterPost.setUserId(userId);
            masterPost.setMasterPostCategory(postCategory);
            masterPost.setContent(createMasterPostRequest.getContent());
            masterPost.setCurrentStreak(0);
            masterPost.setCreatedAt(LocalDateTime.now());
            masterPostRepository.save(masterPost);
            return ResponseEntity.ok(new MessageResponse("Master post created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Category does not exists"));
        }
    }

    public ResponseEntity<?> findPostsByUserId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findByUserId(id));
    }

    public ResponseEntity<?> findPostByPostId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findPostById(id));
    }
}
