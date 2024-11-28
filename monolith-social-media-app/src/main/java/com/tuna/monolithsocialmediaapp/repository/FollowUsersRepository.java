package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.FollowUsers;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUsersRepository extends JpaRepository<FollowUsers, Integer> {
    boolean existsByFollowerIdAndFollowedId(int followerId, int followedId);
}
