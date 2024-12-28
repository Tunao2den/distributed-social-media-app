package com.tuna.notificationservice.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
public class NotificationEntity {
    @Id
    private String id;
    private String receiverId; //notification owner
    private String senderId; //notification owner
    private String message; //notification information
    private boolean isViewed;
    private LocalDateTime timestamp;
}
