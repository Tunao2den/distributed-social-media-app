package com.tuna.usersservice.repository;

import com.tuna.usersservice.model.dto.UserFollowerCountDTO;
import com.tuna.usersservice.model.dto.UsersDTO;
import com.tuna.usersservice.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Users findUsersByUserNameAndPassword(String username, String password);
    @Query("SELECT new com.tuna.usersservice.model.dto.UsersDTO(f.follower.id, f.follower.userName, f.follower.firstName, f.follower.lastName) " +
            "FROM FollowUsers f WHERE f.isRequest = false " +
            "and f.followed.id = :userId")
    List<UsersDTO> findFollowersByUserId(Integer userId);

    @Query("select new com.tuna.usersservice.model.dto.UsersDTO(f.followed.id, f.followed.userName, f.followed.firstName, f.followed.lastName) " +
            "from FollowUsers f where f.isRequest = false " +
            "and f.follower.id =:userId")
    List<UsersDTO> findFollowedUsersById(Integer userId);
    @Query("select new com.tuna.usersservice.model.dto.UsersDTO(u.id, u.userName, u.firstName, u.lastName) " +
            "from Users u " +
            "where u.userName like %:username%")
    List<UsersDTO> searchByUserName(String username);
    @Query(value = "SELECT u.user_id, COUNT(f.followed_id) AS follower_count " +
            "FROM users u " +
            "LEFT JOIN follow_users f ON u.user_id = f.followed_id " +
            "WHERE u.is_private = false and f.is_request = false " +
            "GROUP BY u.user_id " +
            "ORDER BY RAND() " +
            "LIMIT 100", nativeQuery = true)
    List<Object[]> findRandomPublicUsersWithFollowerCount();
}
