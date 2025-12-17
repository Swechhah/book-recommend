package com.platform.recommendor.app.domain.events;

import java.util.List;

public class GeneratedRecommendationsEvent {

    private final Long userId;
    private final Long sourceBookId;
    private final List<Long> recommendedBookIds;

    public GeneratedRecommendationsEvent(
            Long userId,
            Long sourceBookId,
            List<Long> recommendedBookIds
    ) {
        this.userId = userId;
        this.sourceBookId = sourceBookId;
        this.recommendedBookIds = recommendedBookIds;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSourceBookId() {
        return sourceBookId;
    }

    public List<Long> getRecommendedBookIds() {
        return recommendedBookIds;
    }
}
