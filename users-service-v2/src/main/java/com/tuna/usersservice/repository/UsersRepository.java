package com.tuna.usersservice.repository;

import com.tuna.usersservice.model.dto.FollowingUserDTO;
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
    @Query("SELECT new com.tuna.usersservice.model.dto.FollowingUserDTO(f.follower.id, f.follower.userName, f.follower.firstName, f.follower.lastName) " +
            "FROM FollowUsers f WHERE f.followed.id = :userId")
    List<FollowingUserDTO> findFollowersByUserId(Integer userId);

    @Query("select new com.tuna.usersservice.model.dto.FollowingUserDTO(f.followed.id, f.followed.userName, f.followed.firstName, f.followed.lastName) " +
            "from FollowUsers f where f.follower.id =:userId")
    List<FollowingUserDTO> findFollowedUsersById(Integer userId);
    @Query("select new com.tuna.usersservice.model.dto.FollowingUserDTO(u.id, u.userName, u.firstName, u.lastName) " +
            "from Users u " +
            "where u.userName like %:username%")
    List<FollowingUserDTO> searchByUserName(String username);
}
