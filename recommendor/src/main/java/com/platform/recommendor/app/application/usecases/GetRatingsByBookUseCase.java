package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.ports.out.RatingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRatingsByBookUseCase {
    private final RatingsRepository repository;
    public GetRatingsByBookUseCase(RatingsRepository repository) {
        this.repository = repository;
    }
    public List<RatingResponse> execute(Long bookId) {

        List<RatingsModel> ratings =
                repository.getAllRatingsByBookId(bookId);

        return ratings.stream()
                .map(this::toResponse)
                .toList();
    }

    private RatingResponse toResponse(RatingsModel rating) {
        RatingResponse response = new RatingResponse();
        response.setId(rating.getId());
        response.setBookId(rating.getBookId());
        response.setUserId(rating.getUserId());
        response.setRating(rating.getRating());
        return response;
    }

}
