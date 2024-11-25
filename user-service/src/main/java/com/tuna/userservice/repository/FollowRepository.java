package com.tuna.userservice.repository;

import com.tuna.userservice.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    boolean existsByFollowerIdAndFollowedId(Integer followerId, Integer followedId);
}
