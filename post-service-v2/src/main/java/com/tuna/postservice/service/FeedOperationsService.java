package com.tuna.postservice.service;

import com.tuna.postservice.model.entity.DailyPost;
import com.tuna.postservice.model.entity.MasterPost;
import com.tuna.postservice.payload.request.UserAndPublicUsersInfoRequest;
import com.tuna.postservice.payload.response.MessageResponse;
import com.tuna.postservice.repository.DailyPostRepository;
import com.tuna.postservice.repository.MasterPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedOperationsService {

    @Autowired
    private MasterPostRepository masterPostRepository;

    @Autowired
    private DailyPostRepository dailyPostRepository;

    public ResponseEntity<?> getCategoriesByUserId(Integer userId) {
        try {
            masterPostRepository.findMasterPostCategoryIdsByUserId(userId);
            return ResponseEntity.ok(masterPostRepository.findMasterPostCategoryIdsByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not get categories"));
        }
    }

    public List<DailyPost> generateDiscoverFeed(UserAndPublicUsersInfoRequest userAndPublicUsersInfoRequest) {
        Integer userId = userAndPublicUsersInfoRequest.getUserId();
        List<Long> publicUserIds = userAndPublicUsersInfoRequest.getPublicUserIds();
        List<Integer> publicIntegerUserIds = publicUserIds.stream()
                .map(Long::intValue)
                .toList();

        //get master post categories with user id
        List<MasterPost> masterPosts = masterPostRepository.findAllByUserId(userId);
        Set<Integer> userCategoryIdsSet = new HashSet<>();
        for (MasterPost masterPost : masterPosts) {
            userCategoryIdsSet.add(masterPost.getMasterPostCategory().getId());
        }

        if (userCategoryIdsSet.isEmpty()) {
            return findMostLikedLatest100Post(publicIntegerUserIds);
        } else {
            //get master posts with matching categories
            Set<MasterPost> matchingMasterPosts = new HashSet<>();
            for (Long publicUsers : publicUserIds) {
                List<MasterPost> publicUsersMasterPosts =
                        masterPostRepository.findAllByUserIdAndAndMasterPostCategoryList(publicUsers.intValue(), userCategoryIdsSet.stream().toList());
                matchingMasterPosts.addAll(publicUsersMasterPosts);
            }

            if (matchingMasterPosts.isEmpty()) {
                return findMostLikedLatest100Post(publicIntegerUserIds);
            } else {
                Set<DailyPost> dailyPosts = new HashSet<>();
                for (Long id : publicUserIds) {
                    for (MasterPost masterPost : matchingMasterPosts) {
                        if (masterPost.getUserId().equals(id.intValue())) {
                            List<DailyPost> publicUsersDailyPosts =dailyPostRepository.findTop3MostLikedByMasterPost(masterPost);
                            dailyPosts.addAll(publicUsersDailyPosts);
                        }
                    }
                    if (dailyPosts.size() > 100) break;
                }

                if (dailyPosts.isEmpty()) {
                    //get random posts from public users
                    return findMostLikedLatest100Post(publicIntegerUserIds);

                } else if (dailyPosts.size() < 100) {
                    //complete the post count to 100 with random posts
                    List<DailyPost> randomPosts = findMostLikedLatest100Post(publicIntegerUserIds);
                    for (DailyPost randPost : randomPosts) {
                        dailyPosts.add(randPost);
                        if (dailyPosts.size() > 100) break;
                    }

                    return new ArrayList<>(dailyPosts);
                } else {
                    List<DailyPost> dailyPostList = new ArrayList<>(dailyPosts);
                    Collections.shuffle(dailyPostList);
                    return dailyPostList;
                }
            }
        }
    }

    private List<DailyPost> findMostLikedLatest100Post(List<Integer> ids) {
        List<DailyPost> dailyPosts = dailyPostRepository.findDailyPostsByUserIds(ids);

        // Get the latest 1000 posts
        List<DailyPost> latestPosts = dailyPosts.stream()
                .limit(1000)
                .collect(Collectors.toList());

        // Sort by likes.size in descending order
        latestPosts.sort((dp1, dp2) -> Integer.compare(dp2.getLikes().size(), dp1.getLikes().size()));

        // Limit the results to the top 100
        return latestPosts.stream()
                .limit(100)
                .collect(Collectors.toList());
    }
}