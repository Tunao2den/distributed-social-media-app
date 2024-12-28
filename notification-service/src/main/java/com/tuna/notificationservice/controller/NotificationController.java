package com.tuna.notificationservice.controller;

import com.tuna.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<?> getNotificationsForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.getNotificationByUser(userId));
    }
}
