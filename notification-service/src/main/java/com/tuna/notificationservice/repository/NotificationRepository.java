package com.tuna.notificationservice.repository;

import com.tuna.notificationservice.model.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, Integer> {
}
