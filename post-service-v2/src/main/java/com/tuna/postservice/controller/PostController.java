package com.tuna.postservice.controller;

import com.tuna.postservice.payload.request.CreateMasterPostRequest;
import com.tuna.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
