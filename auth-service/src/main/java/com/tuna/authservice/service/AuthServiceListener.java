package com.tuna.authservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.authservice.model.AuthUser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.tuna.authservice.config.KafkaConfiguration.GROUP_ID;

@Service
public class AuthServiceListener {
    private static final String TOPIC = "user_details";
    private final DatabaseService databaseService;

    public AuthServiceListener(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "userKafkaListenerFactory")
    public void consumeJson(String data) throws JsonProcessingException {
        System.out.println("Consumed JSON Message: " + data);

        ObjectMapper objectMapper = new ObjectMapper();
        AuthUser authUser = objectMapper.readValue(data, AuthUser.class);

        databaseService.saveNewUser(authUser);
    }
}
