package com.tuna.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.notificationservice.model.data.NotificationData;
import com.tuna.notificationservice.model.entity.NotificationEntity;
import com.tuna.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.tuna.notificationservice.configuration.KafkaConfiguration.GROUP_ID;
import static com.tuna.notificationservice.constants.TopicConstants.SENT_FOLLOW_REQUEST;

@Service
public class NotificationListenerService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = SENT_FOLLOW_REQUEST, groupId = GROUP_ID, containerFactory = "userKafkaListenerFactory")
    public void createFollowRequestNotification(String data) throws JsonProcessingException {
        System.out.println("Consumed JSON Message: " + data);

        NotificationData notificationData = objectMapper.readValue(data, NotificationData.class);
        NotificationEntity notification = new NotificationEntity();
        notification.setReceiverId(notificationData.getReceiverId());
        notification.setSenderId(notificationData.getSenderId());
        notification.setMessage(notificationData.getMessage());
        notification.setViewed(false);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}
