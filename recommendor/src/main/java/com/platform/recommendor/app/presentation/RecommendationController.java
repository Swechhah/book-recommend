package com.platform.recommendor.app.presentation;

import com.platform.common.application.dto.CommonResponse;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationInfo;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import com.platform.recommendor.app.application.usecases.GetRecommendationsUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/recommendation")
public class RecommendationController {
    private final GetRecommendationsUseCase getRecommendationsUseCase;

    public RecommendationController(GetRecommendationsUseCase useCase) {
        this.getRecommendationsUseCase = useCase;
    }

    @GetMapping("/{bookId}")
    public CommonResponse<RecommendationResponse> recommend(@AuthenticationPrincipal UserDetails user , @PathVariable Long bookId) {
        return CommonResponse.success(getRecommendationsUseCase.execute(user, bookId, 5), "Recommendation Successful");
    }
    @GetMapping
    public CommonResponse<List<RecommendationInfo>> getRecommendations(@AuthenticationPrincipal UserDetails user) {
        return CommonResponse.success(getRecommendationsUseCase.getUserRecommendations(user), "Recommendations done");
    }
}
