package com.tuna.postservice.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMasterPostRequest {
    private Integer userId;
    private Integer masterPostCategoryId;
    private String content;
}