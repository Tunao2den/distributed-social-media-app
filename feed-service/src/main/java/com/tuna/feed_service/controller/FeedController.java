package com.tuna.feed_service.controller;

import com.tuna.feed_service.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/feed/friends")
    public ResponseEntity<?> getFeedForFriends(@RequestParam Integer userId) {
        return feedService.createFriendsFeed(userId);
    }
}
