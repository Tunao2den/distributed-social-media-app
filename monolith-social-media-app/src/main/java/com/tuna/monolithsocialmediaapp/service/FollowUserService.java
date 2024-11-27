package com.tuna.monolithsocialmediaapp.service;

import com.tuna.monolithsocialmediaapp.model.entity.FollowUsers;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import com.tuna.monolithsocialmediaapp.payload.request.FollowUserRequest;
import com.tuna.monolithsocialmediaapp.repository.FollowUsersRepository;
import com.tuna.monolithsocialmediaapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (followUsersRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            throw new RuntimeException(followerName + " is already following " + followedName);
        }
        FollowUsers followUsers = new FollowUsers();
        followUsers.setFollowerId(followerId);
        followUsers.setFollowedId(followedId);
        return followUsersRepository.save(followUsers);
    }
}
