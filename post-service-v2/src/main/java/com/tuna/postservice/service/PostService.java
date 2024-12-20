package com.tuna.postservice.service;

import com.tuna.postservice.model.entity.DailyPost;
import com.tuna.postservice.model.entity.DailyPostComment;
import com.tuna.postservice.model.entity.MasterPost;
import com.tuna.postservice.model.entity.MasterPostCategory;
import com.tuna.postservice.payload.request.CreateCommentRequest;
import com.tuna.postservice.payload.request.CreateDailyPostRequest;
import com.tuna.postservice.payload.request.CreateMasterPostRequest;
import com.tuna.postservice.payload.response.MessageResponse;
import com.tuna.postservice.repository.DailyPostCommentRepository;
import com.tuna.postservice.repository.DailyPostRepository;
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

    @Autowired
    private DailyPostRepository dailyPostRepository;

    @Autowired
    private DailyPostCommentRepository dailyPostCommentRepository;

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
            return ResponseEntity.badRequest().body(new MessageResponse("Could not create the master post"));
        }
    }

    public ResponseEntity<?> findPostsByUserId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findByUserId(id));
    }

    public ResponseEntity<?> findPostByPostId(Integer id) {
        return ResponseEntity.ok(masterPostRepository.findPostById(id));
    }

    public ResponseEntity<?> createDailyPost(CreateDailyPostRequest createDailyPostRequest) {
        Integer userId = createDailyPostRequest.getUserId();
        Integer masterPostId = createDailyPostRequest.getMasterPostId();
        String content = createDailyPostRequest.getContent();
        if (!masterPostRepository.existsById(masterPostId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Master post does not exists"));
        }
        try {
            MasterPost masterPost = masterPostRepository.findPostById(masterPostId);
            if (!masterPost.getUserId().equals(userId)) {
                return ResponseEntity.badRequest().body(new MessageResponse("User does not own this post"));
            }
            DailyPost dailyPost = new DailyPost();
            dailyPost.setUserId(userId);
            dailyPost.setMasterPost(masterPost);
            dailyPost.setContent(content);
            dailyPost.setCreatedAt(LocalDateTime.now());
            dailyPostRepository.save(dailyPost);
            return ResponseEntity.ok(new MessageResponse("Daily post created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not create the daily post"));
        }
    }

    public ResponseEntity<?> getDailyPostsByUserId(Integer userId) {
        return ResponseEntity.ok(dailyPostRepository.findAllByUserId(userId));
    }

    public ResponseEntity<?> getDailyPostsByMasterPostId(Integer masterPostId) {
        return ResponseEntity.ok(dailyPostRepository.findAllByMasterPostId(masterPostId));
    }

    public ResponseEntity<?> createComment(CreateCommentRequest createCommentRequest) {
        Integer userId = createCommentRequest.getUserId();
        Integer dailyPostId = createCommentRequest.getDailyPostId();
        String content = createCommentRequest.getContent();

        if (!dailyPostRepository.existsById(dailyPostId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Daily post does not exits"));
        }
        try {
            DailyPost dailyPost = dailyPostRepository.getReferenceById(dailyPostId);
            DailyPostComment dailyPostComment = new DailyPostComment();
            dailyPostComment.setUserId(userId);
            dailyPostComment.setDailyPost(dailyPost);
            dailyPostComment.setContent(content);
            dailyPostComment.setCreatedAt(LocalDateTime.now());
            dailyPostCommentRepository.save(dailyPostComment);
            return ResponseEntity.ok(new MessageResponse("Comment created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not create the comment"));
        }
    }

    public ResponseEntity<?> getCommentsByDailyPost(Integer dailyPostId) {
        try {
            return ResponseEntity.ok(dailyPostCommentRepository.findAllByDailyPostId(dailyPostId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not get the comments"));
        }
    }

    public ResponseEntity<?> getCommentById(Integer dailyPostCommentId) {
        try {
            return ResponseEntity.ok(dailyPostCommentRepository.findById(dailyPostCommentId).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not get the comment"));
        }
    }
}
