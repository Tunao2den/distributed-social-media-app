package com.tuna.postservice.controller;

import com.tuna.postservice.payload.request.CreateCommentRequest;
import com.tuna.postservice.payload.request.CreateDailyPostRequest;
import com.tuna.postservice.payload.request.CreateMasterPostRequest;
import com.tuna.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/master-post-categories")
    public ResponseEntity<?> addNewMasterCategory(@RequestHeader String postCategory) {
        return postService.createMasterPostCategory(postCategory);
    }

    @GetMapping("/master-post-categories")
    public ResponseEntity<?> getMasterPostCategories() {
        return postService.getAllCategories();
    }

    @PostMapping("/master-posts")
    public ResponseEntity<?> createMasterPost(@RequestBody CreateMasterPostRequest createMasterPostRequest) {
        return postService.createMasterPostToUser(createMasterPostRequest);
    }

    @GetMapping("/master-posts")
    public ResponseEntity<?> getPostsByUserId(@RequestHeader Integer userId) {
        return postService.findPostsByUserId(userId);
    }

    @GetMapping("/master-posts/{postId}")
    public ResponseEntity<?> getPostByPostId(@PathVariable Integer postId) {
        return postService.findPostByPostId(postId);
    }

    @PostMapping("/daily-posts")
    public ResponseEntity<?> createDailyPost(@RequestBody CreateDailyPostRequest createDailyPostRequest) {
        return postService.createDailyPost(createDailyPostRequest);
    }

    @GetMapping("/daily-posts-by-user")
    public ResponseEntity<?> getDailyPosts(@RequestHeader Integer userId) {
        return postService.getDailyPostsByUserId(userId);
    }

    @GetMapping("/daily-posts-by-master-post")
    public ResponseEntity<?> getDailyPostsByMasterPostId(@RequestHeader Integer masterPostId) {
        return postService.getDailyPostsByMasterPostId(masterPostId);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        return postService.createComment(createCommentRequest);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getCommentsByPost(@RequestHeader Integer dailyPostId) {
        return postService.getCommentsByDailyPost(dailyPostId);
    }

    @GetMapping("/comments/{dailyPostCommentId}")
    public ResponseEntity<?> getCommentById(@PathVariable Integer dailyPostCommentId) {
        return postService.getCommentById(dailyPostCommentId);
    }
}
