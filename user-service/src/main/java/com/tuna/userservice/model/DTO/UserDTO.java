package com.tuna.userservice.model.DTO;

import com.tuna.userservice.model.data.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;

    public UserDTO() {}

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setBirthDate(userDTO.getBirthDate());
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));
        user.setPosts(null);
        user.setSavedPosts(null);
        user.setLikes(null);
        user.setFollowers(null);
        user.setFollowing(null);

        return user;
    }
}