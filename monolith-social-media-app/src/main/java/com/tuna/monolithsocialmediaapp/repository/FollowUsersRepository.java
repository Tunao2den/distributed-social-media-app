package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.FollowUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUsersRepository extends JpaRepository<FollowUsers, Integer> {
    boolean existsByFollowerIdAndFollowedId(int followerId, int followedId);
}
