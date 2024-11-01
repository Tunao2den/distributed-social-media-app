package com.tuna.authservice.repository;

import com.tuna.authservice.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthServiceRepository extends JpaRepository<AuthUser, Integer> {
}
