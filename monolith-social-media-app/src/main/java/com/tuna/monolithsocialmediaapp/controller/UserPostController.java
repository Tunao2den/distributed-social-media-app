package com.tuna.monolithsocialmediaapp.controller;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.payload.request.CreateMasterPostRequest;
import com.tuna.monolithsocialmediaapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userName}")
public class UserPostController {

    @Autowired
    private PostService postService;

    @GetMapping("/master-posts")
    public List<MasterPost> getUsersMasterPosts(@PathVariable String userName) {
        return postService.getMasterPostsByUsername(userName);
    }

    @PostMapping("/master-posts")
    public MasterPost addNewMasterPostToUser(@PathVariable String userName, @RequestBody CreateMasterPostRequest createMasterPostRequest) {
        return postService.addNewMasterPostToUser(userName, createMasterPostRequest);
    }

    @GetMapping("/master-posts/daily-posts")
    public List<DailyPost> getAllDailyPostsByMasterPost(@PathVariable String userName) {
        return null;
    }

    @GetMapping("/master-posts/daily-posts/{dailyPostId}")
    public DailyPost getDailyPostByMasterPost(@PathVariable String userName, @PathVariable Integer dailyPostId) {
        return null;
    }
}
