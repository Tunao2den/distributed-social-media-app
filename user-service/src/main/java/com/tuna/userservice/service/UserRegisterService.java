package com.tuna.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuna.userservice.exception.CustomException;
import com.tuna.userservice.exception.CustomExceptionHandler;
import com.tuna.userservice.model.DTO.UserDTO;
import com.tuna.userservice.model.data.AuthUser;
import com.tuna.userservice.model.data.User;
import com.tuna.userservice.model.mapper.AuthUserMapper;
import com.tuna.userservice.model.mapper.UserDTOMapper;
import com.tuna.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    private final UserRepository userRepository;
    private final AuthUserMapper authUserMapper;
    private final UserDTOMapper userDTOMapper;
    private final ObjectMapper objectMapper;
    private ValidationService<User> validationService;

    public UserRegisterService(UserRepository userRepository,
                               AuthUserMapper authUserMapper,
                               UserDTOMapper userDTOMapper,
                               ObjectMapper objectMapper,
                               ValidationService<User> validationService) {
        this.userRepository = userRepository;
        this.authUserMapper = authUserMapper;
        this.userDTOMapper = userDTOMapper;
        this.objectMapper = objectMapper;
        this.validationService = validationService;
    }

    public User registerUser(UserDTO userDTO) throws JsonProcessingException {
        User user = userDTOMapper.toUser(userDTO);
        //return ex if fields are not valid
        var validationSet = validationService.validate(user);
        if (!validationSet.isEmpty()){
            throw new CustomException(validationSet.toString());
        }

        //if fields are valid then send it to auth service


        //if auth service successfully save data, call userRepository.save(user);

        AuthUser authUser = authUserMapper.toAuthUser(userDTO);
        String authUserJson = objectMapper.writeValueAsString(authUser);

        return user;
    }

}
