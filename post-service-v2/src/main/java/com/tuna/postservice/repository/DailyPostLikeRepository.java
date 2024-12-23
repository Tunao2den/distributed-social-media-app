package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.DailyPost;
import com.tuna.postservice.model.entity.DailyPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPostLikeRepository extends JpaRepository<DailyPostLike, Integer> {
    List<DailyPostLike> findAllByDailyPost(DailyPost dailyPost);
    DailyPostLike findByDailyPostAndUserId(DailyPost dailyPost, Integer userId);
    boolean existsByDailyPostAndUserId(DailyPost dailyPost, Integer userId);
}
