package com.platform.recommendor.app.infrastucture.ports;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import org.springframework.stereotype.Component;

@Component
public interface RecommendationClient {
    public RecommendationResponse getRecommendations(String isbn, int topN);
}
