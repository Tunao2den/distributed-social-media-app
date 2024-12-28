package com.tuna.postservice.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LikePostRequest {
    private Integer userId;
    private String userName;
    private Integer dailyPostId;
}
