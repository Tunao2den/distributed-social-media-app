package com.tuna.postservice.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDailyPostRequest {
    private Integer userId;
    private Integer masterPostId;
    private String content;
}
