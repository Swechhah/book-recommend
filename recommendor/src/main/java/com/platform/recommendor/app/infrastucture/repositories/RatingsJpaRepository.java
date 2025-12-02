package com.platform.recommendor.app.infrastucture.repositories;

import com.platform.recommendor.app.infrastucture.entities.RatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingsJpaRepository extends JpaRepository<RatingsEntity, Long> {
    Optional<RatingsEntity> findByUserIdAndBookId(Long userId, Long bookId);
    void deleteAllByUserId(Long userId);
    void deleteAllByBookId(Long bookId);
}
