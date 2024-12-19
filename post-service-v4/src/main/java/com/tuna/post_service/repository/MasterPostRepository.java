package com.tuna.postservice.repository;

import com.tuna.postservice.model.entity.MasterPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterPostRepository extends JpaRepository<MasterPost, Integer> {
    List<MasterPost> findByUserId(Integer id);

    @Query("select t " +
            "from MasterPost t " +
            "where t.id = :id")
    MasterPost findPostById(Integer id);
}
