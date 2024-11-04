package com.tuna.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.userservice.exception.CustomException;
import com.tuna.userservice.model.DTO.UserDTO;
import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.data.User;
import com.tuna.userservice.model.mapper.AuthUserMapper;
import com.tuna.userservice.model.mapper.UserDTOMapper;
import com.tuna.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRegisterService {
    private final UserRepository userRepository;
    private final AuthUserMapper authUserMapper;
    private final UserDTOMapper userDTOMapper;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public UserRegisterService(UserRepository userRepository,
                               AuthUserMapper authUserMapper,
                               UserDTOMapper userDTOMapper,
                               ObjectMapper objectMapper,
                               RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.authUserMapper = authUserMapper;
        this.userDTOMapper = userDTOMapper;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }


    @Transactional
    public User registerUser(UserDTO userDTO) throws JsonProcessingException {
        User user = userDTOMapper.toUser(userDTO);
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new CustomException("This username is already taken");
        }
        userRepository.save(user);

        AuthUser authUser = authUserMapper.toAuthUser(userDTO);
        String authUserJson = objectMapper.writeValueAsString(authUser);
        String url = "http://localhost:8000/auth/receive-user";
        ResponseEntity<Boolean> response =restTemplate.postForEntity(url, authUserJson, Boolean.class);
        if (Boolean.FALSE.equals(response.getBody())) {
            throw new CustomException("Auth server could not handle the data");
        }

        return user;
    }
}