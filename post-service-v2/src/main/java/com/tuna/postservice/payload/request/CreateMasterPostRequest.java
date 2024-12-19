package com.tuna.postservice.payload.request;

public class CreateMasterPostRequest {
    private Integer userId;
    private Integer masterPostCategoryId;
    private String content;

    public CreateMasterPostRequest() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMasterPostCategoryId() {
        return masterPostCategoryId;
    }

    public void setMasterPostCategoryId(Integer masterPostCategoryId) {
        this.masterPostCategoryId = masterPostCategoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}