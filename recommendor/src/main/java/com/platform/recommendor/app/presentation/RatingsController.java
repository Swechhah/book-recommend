package com.platform.recommendor.app.presentation;

import com.platform.common.application.dto.CommonResponse;
import com.platform.recommendor.app.application.dto.rating.RatingRequest;
import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.application.usecases.AddRatingsUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class RatingsController {
    private final AddRatingsUseCase addRatingsUseCase;
    RatingsController(AddRatingsUseCase addRatingsUseCase) {
        this.addRatingsUseCase = addRatingsUseCase;
    }
    @PostMapping
    public CommonResponse<RatingResponse> addRating(@AuthenticationPrincipal UserDetails user, @RequestBody RatingRequest ratingRequest) {
        return CommonResponse.success(addRatingsUseCase.execute(user, ratingRequest), "Rating added");
    }
}
