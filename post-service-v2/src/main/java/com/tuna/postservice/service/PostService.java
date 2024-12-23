package com.tuna.postservice.service;

import com.tuna.postservice.model.entity.*;
import com.tuna.postservice.payload.request.CreateCommentRequest;
import com.tuna.postservice.payload.request.CreateDailyPostRequest;
import com.tuna.postservice.payload.request.CreateMasterPostRequest;
import com.tuna.postservice.payload.request.LikePostRequest;
import com.tuna.postservice.payload.response.LikeDetailsResponse;
import com.tuna.postservice.payload.response.MessageResponse;
import com.tuna.postservice.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private DailyPostLikeRepository dailyPostLikeRepository;

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

    @Transactional
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

    @Transactional
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

    @Transactional
    public ResponseEntity<?> likeDailyPost(LikePostRequest likePostRequest) {
        Integer userId = likePostRequest.getUserId();
        Integer dailyPostId = likePostRequest.getDailyPostId();
        Optional<DailyPost> dailyPost = dailyPostRepository.findById(dailyPostId);
        if (dailyPost.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Post does not exits"));
        }
        if (dailyPostLikeRepository.existsByDailyPostAndUserId(dailyPost.get(), userId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("User already liked the post"));
        }
        try {
            DailyPostLike dailyPostLike = new DailyPostLike();
            dailyPostLike.setUserId(userId);
            dailyPostLike.setDailyPost(dailyPost.get());
            dailyPostLike.setCreatedAt(LocalDateTime.now());
            dailyPostLikeRepository.save(dailyPostLike);
            return ResponseEntity.ok(new MessageResponse("Post liked successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not save the like"));
        }
        // TODO: 23.12.2024 send notification to post owner
    }

    public ResponseEntity<?> getLikesOfDailyPost(Integer dailyPostId) {
        Optional<DailyPost> dailyPost = dailyPostRepository.findById(dailyPostId);
        if (dailyPost.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Post does not exits"));
        }
        try {
            List<DailyPostLike> dailyPostLikes = dailyPostLikeRepository.findAllByDailyPost(dailyPost.get());
            List<Integer> userIds = new ArrayList<>();
            for (DailyPostLike dailyPostLike : dailyPostLikes) {
                userIds.add(dailyPostLike.getUserId());
            }
            LikeDetailsResponse likeDetailsResponse = new LikeDetailsResponse();
            likeDetailsResponse.setUserIds(userIds);
            likeDetailsResponse.setLikeCount(userIds.size());
            return ResponseEntity.ok(likeDetailsResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not get the like credentials"));
        }
    }

    @Transactional
    public ResponseEntity<?> unlikeDailyPost(LikePostRequest likePostRequest) {
        Integer userId = likePostRequest.getUserId();
        Integer dailyPostId = likePostRequest.getDailyPostId();
        Optional<DailyPost> dailyPost = dailyPostRepository.findById(dailyPostId);
        if (dailyPost.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Post does not exits"));
        }
        try {
            DailyPostLike dailyPostLike = dailyPostLikeRepository.findByDailyPostAndUserId(dailyPost.get(), userId);
            dailyPostLikeRepository.delete(dailyPostLike);
            return ResponseEntity.ok(new MessageResponse("Post unliked successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not save the unlike"));
        }
    }
}
