package com.tuna.notificationservice.controller;

import com.tuna.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<?> test() {
        notificationService.createNotification();
        return ResponseEntity.ok("success");
    }
}
