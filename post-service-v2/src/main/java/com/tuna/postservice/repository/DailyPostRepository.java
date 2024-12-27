package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.DailyPost;
import com.tuna.postservice.model.entity.MasterPost;
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

    @Query(value = " SELECT daily_post_id, user_id, master_post_id, content, created_at " +
            "FROM ( SELECT * FROM daily_post " +
            "WHERE master_post_id = :#{#masterPost.id} " +
            "ORDER BY created_at DESC LIMIT 10) AS recent_posts " +
            "ORDER BY ( SELECT COUNT(*) FROM daily_post_like dpl " +
            "WHERE dpl.daily_post_id = recent_posts.daily_post_id) DESC LIMIT 3 ", nativeQuery = true)
    List<DailyPost> findTop3MostLikedByMasterPost(MasterPost masterPost);

    @Query("SELECT dp FROM DailyPost dp " +
            "WHERE dp.userId IN :ids " +
            "ORDER BY dp.createdAt DESC")
    List<DailyPost> findDailyPostsByUserIds(List<Integer> ids);
}
