package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.DailyPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPostCommentRepository extends JpaRepository<DailyPostComment, Integer> {
    @Query("select dc " +
            "from DailyPostComment dc " +
            "join DailyPost d on d.id = dc.dailyPost.id " +
            "where dc.dailyPost.id = :dailyPostId")
    List<DailyPostComment> findAllByDailyPostId(Integer dailyPostId);
}
