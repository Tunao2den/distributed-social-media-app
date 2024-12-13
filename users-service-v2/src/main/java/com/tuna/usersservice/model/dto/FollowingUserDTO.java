package com.tuna.usersservice.model.dto;

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
}
