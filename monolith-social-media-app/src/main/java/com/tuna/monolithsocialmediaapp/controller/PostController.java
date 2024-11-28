package com.tuna.monolithsocialmediaapp.controller;

import com.tuna.monolithsocialmediaapp.model.entity.MasterPostCategory;
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
    public MasterPostCategory addNewMasterCategory(@RequestHeader String category) {
        return postService.addNewCategory(category);
    }
}
