package com.tuna.usersservice.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationData {
    private String receiverId; //notification owner
    private String senderId; //notification sender
    private String message; //notification information
}
