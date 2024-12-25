package com.tuna.usersservice.controller;

import com.tuna.usersservice.service.FeedOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedOpsController {

    @Autowired
    private FeedOperationsService feedOperationsService;

    @GetMapping("/users-and-followers")
    public ResponseEntity<?> getPublicUsersAndFollowersCount() {
        return feedOperationsService.findPublicFollowerWithFollowerCounts();
    }
}
