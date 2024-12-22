package com.tuna.usersservice.repository;

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
}
