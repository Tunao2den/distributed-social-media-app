package com.tuna.monolithsocialmediaapp.controller;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.payload.request.CreateDailyPostRequest;
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

    @GetMapping("/master-posts/{masterPostId}")
    public MasterPost getUsersMasterPost(@PathVariable String userName, @PathVariable Integer masterPostId) {
        return postService.getMasterPostByUsernameAndMasterPostId(userName, masterPostId);
    }

    @GetMapping("/master-posts/{masterPostId}/daily-posts")
    public List<DailyPost> getAllDailyPostsByMasterPost(@PathVariable String userName, @PathVariable Integer masterPostId) {
        return postService.getUsersAllDailyPostsByMasterPost(userName, masterPostId);
    }

    @PostMapping("/master-posts/{masterPostId}/daily-posts")
    public DailyPost addNewDailyPostToUser(
            @PathVariable String userName, @PathVariable Integer masterPostId, @RequestBody CreateDailyPostRequest createDailyPostRequest) {
        return postService.addNewDailyPostToUser(userName, masterPostId, createDailyPostRequest);
    }

    @GetMapping("/master-posts/{masterPostId}/daily-posts/{dailyPostId}")
    public DailyPost getDailyPostByUsernameAndMasterPostIdAndDailyPostId(
            @PathVariable String userName, @PathVariable Integer masterPostId, @PathVariable Integer dailyPostId) {
        return postService.getDailyPostByUsernameAndMasterPostIdAndDailyPostId(userName, masterPostId, dailyPostId);
    }
}
