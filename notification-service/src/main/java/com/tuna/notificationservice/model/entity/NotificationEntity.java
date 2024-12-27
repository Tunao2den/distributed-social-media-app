package com.tuna.notificationservice.model.entity;

import com.tuna.notificationservice.model.DTO.NotificationDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
public class NotificationEntity {
    @Id
    private Integer id;
    private Integer userId; //notification owner
    private String type; //notification type
    private NotificationDTO notification; //notification information
}
