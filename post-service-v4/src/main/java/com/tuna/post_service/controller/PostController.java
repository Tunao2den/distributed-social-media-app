package com.tuna.postservice.controller;

import com.tuna.postservice.model.entity.MasterPostCategory;
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
    public List<MasterPostCategory> getMasterPostCategories() {
        return postService.getAllCategoriesList();
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getUsersPosts(@RequestHeader Integer userId) {
        return postService.findUsersPostsByUserId(userId);
    }
}
