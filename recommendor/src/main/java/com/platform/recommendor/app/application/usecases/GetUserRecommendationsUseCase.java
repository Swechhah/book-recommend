package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationInfo;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.ports.out.RecommendationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserRecommendationsUseCase {
    private final RecommendationRepository recommendationRepository;
    public GetUserRecommendationsUseCase(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }
    public List<RecommendationInfo> execute(UserDetails user) {
        List<RecommendationModel> recommendationModels = recommendationRepository.getRecommendationByUser(user.getUsername());
        return recommendationModels.stream().map(recommendationModel -> {
            RecommendationInfo info = new RecommendationInfo();
            info.setRecommendBookId(recommendationModel.getRecommendBookId());
            info.setRecommendedBookIds(recommendationModel.getRecommendedBookIds());
            return info;
        }).toList();
    }
}
