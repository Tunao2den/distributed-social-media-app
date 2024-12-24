package com.tuna.feed_service.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyPostDTO {
    private Integer id;
    private Integer userId;
    private String content;
    private LocalDateTime createdAt;
}
