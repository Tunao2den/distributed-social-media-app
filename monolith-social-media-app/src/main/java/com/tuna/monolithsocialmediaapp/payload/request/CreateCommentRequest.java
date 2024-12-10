package com.tuna.monolithsocialmediaapp.payload.request;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private Integer userId;
    private String content;
}
