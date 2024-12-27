package com.tuna.postservice.controller;

import com.tuna.postservice.payload.request.UserAndPublicUsersInfoRequest;
import com.tuna.postservice.service.FeedOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedOpsController {

    @Autowired
    private FeedOperationsService feedOperationsService;

    @GetMapping("/users-post-categories")
    public ResponseEntity<?> getPublicUsersAndFollowersCount(@RequestParam Integer userId) {
        return feedOperationsService.getCategoriesByUserId(userId);
    }

    @PostMapping("/generate-discover-feed")
    public ResponseEntity<?> generateDiscoverFeed(@RequestBody UserAndPublicUsersInfoRequest userAndPublicUsersInfoRequest) {
        return ResponseEntity.ok(feedOperationsService.generateDiscoverFeed(userAndPublicUsersInfoRequest));
    }
}
