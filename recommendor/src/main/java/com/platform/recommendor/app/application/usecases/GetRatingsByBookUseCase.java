package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;

import java.util.List;

public class GetRatingsByBookUseCase {
    private final BookRecommendorRepository repository;
    public GetRatingsByBookUseCase(BookRecommendorRepository repository) {
        this.repository = repository;
    }
    public List<RatingResponse>  getRatingsByBook(String bookId){
        List<RatingsModel> ratings = repository.getRa
    }
}
