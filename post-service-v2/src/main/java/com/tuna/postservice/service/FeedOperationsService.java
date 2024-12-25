package com.tuna.usersservice.service;

import com.tuna.usersservice.model.dto.UserFollowerCountDTO;
import com.tuna.usersservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedOperationsService {

    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<?> findPublicFollowerWithFollowerCounts() {
        List<Object[]> results = usersRepository.findRandomPublicUsersWithFollowerCount();
        List<UserFollowerCountDTO> users = new ArrayList<>();

        for (Object[] result : results) {
            Long userId = (Long) result[0];
            Long followerCount = (Long) result[1];
            users.add(new UserFollowerCountDTO(userId, followerCount));
        }

        return ResponseEntity.ok(users);
    }
}
