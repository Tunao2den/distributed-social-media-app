package com.tuna.notificationservice.service;

import com.tuna.notificationservice.exception.NotificationServiceException;
import com.tuna.notificationservice.model.entity.NotificationEntity;
import com.tuna.notificationservice.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public List<NotificationEntity> getNotificationByUser(Integer userId) {
        try {
            List<NotificationEntity> notificationEntities = notificationRepository.findAllByReceiverId(userId.toString());
            if (notificationEntities.isEmpty()) {
                logger.info("No notifications found for user with ID: {}", userId);
            }
            return notificationEntities.stream()
                    .sorted(Comparator.comparing(NotificationEntity::getTimestamp).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An error occurred while fetching notifications for user with ID: {}", userId, e);
            throw new NotificationServiceException("Failed to fetch notifications for user: " + userId, e);
        }
    }
}
