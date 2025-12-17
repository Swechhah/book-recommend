package com.platform.recommendor.app.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.platform.common.application.dto.CommonResponse;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationInfo;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import com.platform.recommendor.app.application.usecases.GetRecommendationsUseCase;
import com.platform.recommendor.app.application.usecases.GetUserRecommendationsUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/recommendation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendationController {
    private final GetRecommendationsUseCase getRecommendationsUseCase;
    private final GetUserRecommendationsUseCase getUserRecommendationsUseCase;

    public RecommendationController(GetRecommendationsUseCase useCase,  GetUserRecommendationsUseCase getUserRecommendationsUseCase) {
        this.getRecommendationsUseCase = useCase;
        this.getUserRecommendationsUseCase = getUserRecommendationsUseCase;
    }

    @GetMapping("/{bookId}")
    public CommonResponse<RecommendationResponse> recommend(@AuthenticationPrincipal UserDetails user , @PathVariable Long bookId) {
        return CommonResponse.success(getRecommendationsUseCase.execute(user, bookId, 5), "Recommendation Successful");
    }
    @GetMapping
    public CommonResponse<List<RecommendationInfo>> getRecommendations(@AuthenticationPrincipal UserDetails user) {
        return CommonResponse.success(getUserRecommendationsUseCase.execute(user), "Recommendations done");
    }
}
