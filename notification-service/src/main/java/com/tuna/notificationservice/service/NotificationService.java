package com.tuna.notificationservice.service;

import com.tuna.notificationservice.model.DTO.NotificationDTO;
import com.tuna.notificationservice.model.entity.NotificationEntity;
import com.tuna.notificationservice.repository.MongoRepository;
import com.tuna.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification() {
        NotificationEntity notification = new NotificationEntity();
        notification.setId(2);
        notification.setUserId(3);
        notification.setType("user service");

        NotificationDTO details = new NotificationDTO();
        details.setTitle("Follow request");
        details.setMessage("User 7 accepted your follow request");

        notification.setNotification(details);

        notificationRepository.save(notification);
    }
}
