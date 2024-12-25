package com.tuna.usersservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowerCountDTO {
    private Long userId;
    private Long followerCount;
}
