package com.tuna.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.userservice.exception.CustomException;
import com.tuna.userservice.model.entity.Users;
import com.tuna.userservice.payload.request.UserRequest;
import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.mapper.AuthUserMapper;
import com.tuna.userservice.model.mapper.UserRequestMapper;
import com.tuna.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRegisterService {
    private final UserRepository userRepository;
    private final AuthUserMapper authUserMapper;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public UserRegisterService(UserRepository userRepository,
                               AuthUserMapper authUserMapper,
                               UserRequestMapper userRequestMapper,
                               ObjectMapper objectMapper,
                               RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.authUserMapper = authUserMapper;
        this.userRequestMapper = userRequestMapper;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }


    @Transactional
    public Users registerUser(UserRequest userRequest) throws JsonProcessingException {
        Users users = userRequestMapper.toUser(userRequest);
        if (userRepository.existsByUserName(users.getUserName())) {
            throw new CustomException("This username is already taken");
        }
        userRepository.save(users);

        AuthUser authUser = authUserMapper.toAuthUser(userRequest);
        String authUserJson = objectMapper.writeValueAsString(authUser);
        // TODO: 12.11.2024 refactor the naming server to fix the path
        String url = "http://auth-service/auth-service/receive-user";
        ResponseEntity<Boolean> response =restTemplate.postForEntity(url, authUserJson, Boolean.class);
        if (Boolean.FALSE.equals(response.getBody())) {
            throw new CustomException("Auth server could not handle the data");
        }

        return users;
    }
}