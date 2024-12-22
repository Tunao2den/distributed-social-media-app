package com.tuna.usersservice.model.dto;

import com.tuna.usersservice.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowingUserDTO {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;

    public FollowingUserDTO toFollowingUserDTO(Users user) {
        FollowingUserDTO followingUserDTO = new FollowingUserDTO();
        followingUserDTO.setId(user.getId());
        followingUserDTO.setUserName(user.getUserName());
        followingUserDTO.setFirstName(user.getFirstName());
        followingUserDTO.setLastName(user.getLastName());
        return followingUserDTO;
    }
}
