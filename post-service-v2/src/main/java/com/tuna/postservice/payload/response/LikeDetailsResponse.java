package com.tuna.postservice.payload.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class LikeDetailsResponse {
    private List<Integer> userIds;
    private Integer likeCount;
}
