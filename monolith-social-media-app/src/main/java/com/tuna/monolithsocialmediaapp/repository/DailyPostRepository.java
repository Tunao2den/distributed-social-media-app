package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyPostRepository extends JpaRepository<DailyPost, Integer> {
    List<DailyPost> findByUsersAndMasterPost(Users user, MasterPost masterPost);

    DailyPost getDailyPostByUsersAndMasterPostAndId(Users user, MasterPost masterPost, Integer id);
}
