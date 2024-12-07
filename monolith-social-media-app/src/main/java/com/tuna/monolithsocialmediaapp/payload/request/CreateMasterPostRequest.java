package com.tuna.monolithsocialmediaapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMasterPostRequest {
    private Integer userId;
    private Integer masterPostCategoryId;
    private String content;
}
