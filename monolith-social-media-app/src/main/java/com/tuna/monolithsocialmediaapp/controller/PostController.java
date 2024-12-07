package com.tuna.monolithsocialmediaapp.controller;

import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPostCategory;
import com.tuna.monolithsocialmediaapp.payload.request.CreateMasterPostRequest;
import com.tuna.monolithsocialmediaapp.payload.request.MasterPostCategoryRequest;
import com.tuna.monolithsocialmediaapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/master-post-categories")
    public List<MasterPostCategory> getAllMasterPostCategories() {
        return postService.getAllCategories();
    }

    @PostMapping("/master-post-categories")
    public MasterPostCategory addNewMasterCategory(@RequestBody MasterPostCategoryRequest category) {
        return postService.addNewCategory(category);
    }

    @GetMapping("/master-posts")
    public List<MasterPost> getAllMasterPosts() {
        return postService.getAllMasterPosts();
    }

    @PostMapping("/master-posts")
    public MasterPost addNewMasterPost(@RequestBody CreateMasterPostRequest createMasterPostRequest) {
        return postService.addNewMasterPost(createMasterPostRequest);
    }
}
