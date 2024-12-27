package com.tuna.feed_service.service;

import com.tuna.feed_service.model.DTO.UserFollowerCountDTO;
import com.tuna.feed_service.payload.request.UserAndPublicUsersInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscoverFeedService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Send requests to post service to create discover feed
     *
     * @param userId owner of the feed
     */
    public ResponseEntity<?> createDiscoverFeed(Integer userId) {

        //random public userların idlerini ve kullanıcı sayılarını topla
        String USER_SERVICE_URL = "http://localhost:8100/users-and-followers";
        ResponseEntity<List<UserFollowerCountDTO>> userFollowerCountResponseEntity = restTemplate.exchange(
                USER_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserFollowerCountDTO>>() {
                }
        );

        List<UserFollowerCountDTO> userFollowerCountDTOS = userFollowerCountResponseEntity.getBody();
        userFollowerCountDTOS.sort(Comparator.comparingLong(UserFollowerCountDTO::getFollowerCount).reversed());

        List<Long> publicUserIds = new ArrayList<>();

        for (UserFollowerCountDTO publicUser : userFollowerCountDTOS) {
            publicUserIds.add(publicUser.getUserId());
        }

        //user id ile public userların idlerini feed oluşturması için posts service gönder
        UserAndPublicUsersInfoRequest userAndPublicUsersInfoRequest = new UserAndPublicUsersInfoRequest();
        userAndPublicUsersInfoRequest.setUserId(userId);
        userAndPublicUsersInfoRequest.setPublicUserIds(publicUserIds);

        String postsServiceUrl = "http://localhost:8200/generate-discover-feed";

        HttpEntity<UserAndPublicUsersInfoRequest> requestEntity = new HttpEntity<>(userAndPublicUsersInfoRequest);

        ResponseEntity<?> response = restTemplate.postForEntity(postsServiceUrl, requestEntity, Object.class);

        return response;
    }
}
