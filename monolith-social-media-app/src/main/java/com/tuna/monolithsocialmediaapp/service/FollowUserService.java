package com.tuna.monolithsocialmediaapp.service;

import com.tuna.monolithsocialmediaapp.model.entity.FollowUsers;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import com.tuna.monolithsocialmediaapp.payload.request.FollowUserRequest;
import com.tuna.monolithsocialmediaapp.repository.FollowUsersRepository;
import com.tuna.monolithsocialmediaapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowUserService {

    @Autowired
    private FollowUsersRepository followUsersRepository;

    @Autowired
    private UsersRepository usersRepository;
    public FollowUsers sendFollowRequest(FollowUserRequest followUserRequest) {
        String followerName = followUserRequest.getFollowerName();
        String followedName = followUserRequest.getFollowedName();
        int followerId = usersRepository.findFirstByUserName(followerName).getId();
        int followedId = usersRepository.findFirstByUserName(followedName).getId();
        Optional<Users> followerUser = usersRepository.findById(followerId);
        Optional<Users> followedUser = usersRepository.findById(followedId);
        if (followUsersRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            throw new RuntimeException(followerName + " is already following " + followedName);
        }
        FollowUsers followUsers = new FollowUsers();
        followUsers.setFollower(followerUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
        followUsers.setFollowed(followedUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
        return followUsersRepository.save(followUsers);
    }
}
