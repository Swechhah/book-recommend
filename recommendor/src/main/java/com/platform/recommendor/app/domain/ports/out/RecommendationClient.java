package com.platform.recommendor.app.domain.ports.out;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;

public interface RecommendationClient {
    public RecommendationResponse getRecommendations(String isbn, int topN);
}
