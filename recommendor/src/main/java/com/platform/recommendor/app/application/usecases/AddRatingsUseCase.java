package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.rating.RatingRequest;
import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AddRatingsUseCase {
    private final BookRecommendorRepository repository;
    public AddRatingsUseCase(BookRecommendorRepository repository) {
        this.repository = repository;
    }
    public RatingResponse execute(UserDetails user, RatingRequest ratingRequest) {
        RatingsModel ratingsModel = new RatingsModel();
        ratingsModel.setBookId(ratingRequest.getBookId());
        ratingsModel.setRating(ratingRequest.getRating());
        UserModel userModel = repository.getUserByUsername(user.getUsername()).get();
        ratingsModel.setUserId(userModel.getId());
        RatingsModel savedRating = repository.saveRating(ratingsModel);
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setId(savedRating.getId());
        ratingResponse.setBookId(savedRating.getBookId());
        ratingResponse.setUserId(savedRating.getUserId());
        ratingResponse.setRating(savedRating.getRating());
        return ratingResponse;
    }

}
