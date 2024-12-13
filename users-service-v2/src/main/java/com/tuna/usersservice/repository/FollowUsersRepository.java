package com.tuna.usersservice.repository;

import com.tuna.usersservice.model.entity.FollowUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUsersRepository extends JpaRepository<FollowUsers, Integer> {
    boolean existsByFollowerIdAndFollowedId(int followerId, int followedId);
}