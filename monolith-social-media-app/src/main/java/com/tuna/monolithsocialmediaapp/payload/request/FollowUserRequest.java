package com.tuna.monolithsocialmediaapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserRequest {
    private String followerName;
    private String followedName;
}
