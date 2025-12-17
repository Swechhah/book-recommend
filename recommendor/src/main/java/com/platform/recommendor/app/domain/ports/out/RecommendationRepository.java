package com.platform.recommendor.app.domain.ports.out;

import com.platform.recommendor.app.domain.model.RecommendationModel;

import java.util.List;
public interface RecommendationRepository {
    RecommendationModel saveRecommendations(RecommendationModel recommendationModel);
    RecommendationModel getRecommendationById(Long id);
    List<RecommendationModel> getRecommendationByUser(String username);

}
