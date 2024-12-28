package com.tuna.postservice.model.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class NotificationData {
    private String receiverId; //notification owner
    private String senderId; //notification sender
    private String message; //notification information
}
