package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.DailyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPostRepository extends JpaRepository<DailyPost, Integer> {
    List<DailyPost> findAllByUserId(Integer userId);

    @Query("select d " +
            "from DailyPost d " +
            "join MasterPost m on d.masterPost.id = m.id " +
            "where m.id = :masterPostId")
    List<DailyPost> findAllByMasterPostId(Integer masterPostId);
}
