package com.tuna.usersservice.repository;

import com.tuna.usersservice.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Users findUsersByUserNameAndPassword(String username, String password);
}
