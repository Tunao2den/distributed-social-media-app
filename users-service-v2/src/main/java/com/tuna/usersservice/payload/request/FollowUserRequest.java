package com.tuna.usersservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserRequest {
    private Integer followerId;
    private Integer followedId;
}