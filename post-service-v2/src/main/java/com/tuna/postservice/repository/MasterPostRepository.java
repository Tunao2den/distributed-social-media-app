package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.MasterPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterPostRepository extends JpaRepository<MasterPost, Integer> {
    List<MasterPost> findByUserId(Integer id);

    @Query("select t " +
            "from MasterPost t " +
            "where t.id = :id")
    MasterPost findPostById(Integer id);

    @Query("SELECT DISTINCT mp.masterPostCategory.id FROM MasterPost mp WHERE mp.userId = :userId")
    List<Integer> findMasterPostCategoryIdsByUserId(@Param("userId") Integer userId);

    List<MasterPost> findAllByUserId(Integer userId);

    @Query("select mp " +
            "from MasterPost mp " +
            "where mp.userId = :userId " +
            "and mp.masterPostCategory.id in (:masterPostCategories) " +
            "order by mp.createdAt desc " +
            "limit 10")
    List<MasterPost> findAllByUserIdAndAndMasterPostCategoryList(Integer userId, List<Integer> masterPostCategories);
}
