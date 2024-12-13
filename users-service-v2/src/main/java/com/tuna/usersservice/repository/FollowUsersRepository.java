package com.tuna.usersservice.repository;

import com.tuna.usersservice.model.entity.FollowUsers;
import com.tuna.usersservice.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUsersRepository extends JpaRepository<FollowUsers, Integer> {
    boolean existsByFollowerIdAndFollowedId(int followerId, int followedId);
    @Query("SELECT f1.followed.id " +
            "FROM FollowUsers f1 JOIN FollowUsers f2 " +
            "ON f1.follower.id = f2.followed.id AND f1.followed.id = f2.follower.id " +
            "WHERE f1.follower.id = :userId")
    List<Integer> findMutualFollowingIds(@Param("userId") Integer userId);
    @Query("select f.followed " +
            "from FollowUsers f " +
            "where f.follower.id in :userIds")
    List<Users> findFollowedUsers(@Param("userIds") List<Integer> userIds);
    @Query("select f.followed.id " +
            "from FollowUsers f " +
            "where f.follower.id = :userId")
    List<Integer> findAlreadyFollowingUserIds(@Param("userId") Integer userId);
}