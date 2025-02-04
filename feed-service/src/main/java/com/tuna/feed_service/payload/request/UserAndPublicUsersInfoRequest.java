package com.tuna.feed_service.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserAndPublicUsersInfoRequest {
    private Integer userId;
    private List<Long> publicUserIds;
}
