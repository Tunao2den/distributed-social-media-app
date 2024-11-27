package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Users findFirstByUserName(String userName);
}
