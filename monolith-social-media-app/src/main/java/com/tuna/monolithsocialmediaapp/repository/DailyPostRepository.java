package com.tuna.monolithsocialmediaapp.repository;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPostRepository extends JpaRepository<DailyPost, Integer> {
}
