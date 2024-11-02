package com.tuna.userservice.repository;

import com.tuna.userservice.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String field);
}