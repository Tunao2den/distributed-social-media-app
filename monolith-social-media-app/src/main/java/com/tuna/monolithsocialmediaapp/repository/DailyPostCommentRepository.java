package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import com.tuna.monolithsocialmediaapp.model.entity.DailyPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPostCommentRepository extends JpaRepository<DailyPostComment, Integer> {
    List<DailyPostComment> findAllByDailyPost(DailyPost dailyPost);
}
