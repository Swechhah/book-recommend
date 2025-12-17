package com.platform.recommendor.app.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.platform.common.application.dto.CommonResponse;
import com.platform.recommendor.app.application.dto.rating.RatingRequest;
import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.application.usecases.AddRatingsUseCase;
import com.platform.recommendor.app.application.usecases.GetRatingsByBookUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingsController {
    private final AddRatingsUseCase addRatingsUseCase;
    private final GetRatingsByBookUseCase getRatingsByBookUseCase;
    RatingsController(AddRatingsUseCase addRatingsUseCase, GetRatingsByBookUseCase getRatingsByBookUseCase) {
        this.addRatingsUseCase = addRatingsUseCase;
        this.getRatingsByBookUseCase = getRatingsByBookUseCase;
    }
    @PostMapping
    public CommonResponse<RatingResponse> addRating(@AuthenticationPrincipal UserDetails user, @RequestBody RatingRequest ratingRequest) {
        return CommonResponse.success(addRatingsUseCase.execute(user, ratingRequest), "Rating added");
    }
    @GetMapping
    public CommonResponse<List<RatingResponse>> getRatings(@RequestParam Long bookId) {
        return CommonResponse.success(getRatingsByBookUseCase.execute(bookId), "Rating list");
    }
}
