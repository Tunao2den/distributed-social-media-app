package com.tuna.postservice.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateCommentRequest {
    private Integer userId;
    private Integer dailyPostId;
    private String content;
}
