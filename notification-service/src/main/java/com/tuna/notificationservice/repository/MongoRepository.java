package com.tuna.notificationservice.repository;


import com.tuna.notificationservice.model.entity.NotificationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<NotificationEntity, Integer> {
}
