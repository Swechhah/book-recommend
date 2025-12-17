package com.platform.recommendor.app.infrastructure.repositories;

import com.platform.recommendor.app.infrastructure.entities.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationJpaRepository extends JpaRepository<RecommendationEntity, Long> {
    List<RecommendationEntity> findByUserId(Long userId);
}
