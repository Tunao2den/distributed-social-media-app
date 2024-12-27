package com.tuna.feed_service.controller;

import com.tuna.feed_service.service.DiscoverFeedService;
import com.tuna.feed_service.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private DiscoverFeedService discoverFeedService;

    @GetMapping("/feed/friends")
    public ResponseEntity<?> getFeedForFriends(@RequestParam Integer userId) {
        return feedService.createFriendsFeed(userId);
    }

    @PostMapping("/feed/discover")
    public ResponseEntity<?> createDiscoverFeed(@RequestParam Integer userId) {
        return discoverFeedService.createDiscoverFeed(userId);
    }
}
