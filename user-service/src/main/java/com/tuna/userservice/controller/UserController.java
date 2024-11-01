package com.tuna.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.userservice.model.DTO.UserDTO;
import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.data.User;
import com.tuna.userservice.model.mapper.AuthUserMapper;
import com.tuna.userservice.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "user_details";

    public UserController(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) throws JsonProcessingException {
        UserDTO newUser = new UserDTO();
        User user = newUser.toUser(userDTO);
        userRepository.save(user);
        AuthUserMapper authUserMapper = new AuthUserMapper();
        AuthUser authUser = authUserMapper.toAuthUser(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String authUserJson = objectMapper.writeValueAsString(authUser);

        System.out.println(authUserJson);
        kafkaTemplate.send(TOPIC, authUserJson);

        return user;
    }
}
