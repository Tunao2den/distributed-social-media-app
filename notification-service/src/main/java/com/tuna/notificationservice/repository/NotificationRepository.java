package com.tuna.notificationservice.repository;

import com.tuna.notificationservice.model.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, Integer> {
    List<NotificationEntity> findAllByReceiverId(String receiverId);
}
