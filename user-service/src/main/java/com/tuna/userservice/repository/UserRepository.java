package com.tuna.userservice.repository;

import com.tuna.userservice.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByUserName(String field);

    Users findUsersByUserName(String userName);
}