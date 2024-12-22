package com.tuna.usersservice.model.dto;

import com.tuna.usersservice.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;

    public UsersDTO toFollowingUserDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        usersDTO.setUserName(user.getUserName());
        usersDTO.setFirstName(user.getFirstName());
        usersDTO.setLastName(user.getLastName());
        return usersDTO;
    }
}
