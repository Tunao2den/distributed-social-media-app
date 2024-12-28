package com.tuna.notificationservice.controller;

import com.tuna.notificationservice.service.NotificationListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationListenerService notificationListenerService;

    @GetMapping("/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("success");
    }
}
