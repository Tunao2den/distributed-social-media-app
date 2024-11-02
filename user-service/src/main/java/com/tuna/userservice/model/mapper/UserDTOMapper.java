package com.tuna.userservice.model.mapper;

import com.tuna.userservice.model.DTO.UserDTO;
import com.tuna.userservice.model.data.User;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
@Component
public class UserDTOMapper {
    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));

        return user;
    }
}
