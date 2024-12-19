package com.tuna.userservice.service;

import com.tuna.userservice.model.entity.Users;
import com.tuna.userservice.repository.FollowRepository;
import com.tuna.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    public UserFollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void sendFollowRequest(String follower, String followed) {
        Users followerUser = userRepository.findUsersByUserName(follower);
        Users followedUser = userRepository.findUsersByUserName(followed);

//        followRepository.save(followerUser, followedUser);
    }

    public void acceptFollowRequest(String follower, String Followed) {

    }
}
