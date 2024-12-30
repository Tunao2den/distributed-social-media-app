package com.tuna.feed_service.service;

import com.tuna.feed_service.model.DTO.DailyPostDTO;
import com.tuna.feed_service.model.DTO.UsersDTO;
import com.tuna.feed_service.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     *Send requests to user and post services to create feed
     *
     * @param userId owner of the feed
     */
    public ResponseEntity<?> createFriendsFeed(Integer userId) {
//        String url = "http://localhost:8100/followed?userId={userId}";
        String url = "http://users-service-v2/followed?userId={userId}";

        ResponseEntity<List<UsersDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // no request body
                new ParameterizedTypeReference<List<UsersDTO>>() {},
                userId
        );

        List<UsersDTO> followedUsers;
        if (response.getStatusCode().is2xxSuccessful()) {
            followedUsers = response.getBody();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Could not get the followings"));
        }

//        String postServiceUrl = "http://localhost:8200/daily-posts-by-user";
        String postServiceUrl = "http://post-service-v2/daily-posts-by-user";
        List<DailyPostDTO> dailyPosts = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();

        for (UsersDTO usersDTO : followedUsers) {
            Integer followedUserId = usersDTO.getId();
            headers.set("userId", followedUserId.toString());

            ResponseEntity<List<DailyPostDTO>> postResponse = restTemplate.exchange(
                    postServiceUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<DailyPostDTO>>() {}
            );

            if (postResponse.getStatusCode().is2xxSuccessful()) {
                dailyPosts.addAll(postResponse.getBody());
            }

            if (dailyPosts.size() >= 100) {
                break;
            }
        }

        dailyPosts.sort((post1, post2) -> post2.getCreatedAt().compareTo(post1.getCreatedAt()));

        return ResponseEntity.ok(dailyPosts);
    }
}
