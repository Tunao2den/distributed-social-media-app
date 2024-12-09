package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterPostRepository extends JpaRepository<MasterPost, Integer> {
    List<MasterPost> findByUser(Users users);

    MasterPost findByUserAndAndId(Users user, Integer id);
}
