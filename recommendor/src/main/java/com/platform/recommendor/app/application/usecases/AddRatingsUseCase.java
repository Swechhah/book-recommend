package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.rating.RatingRequest;
import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.domain.events.BookRatedEvent;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.domain.ports.out.DomainPublisherEvent;
import com.platform.recommendor.app.domain.ports.out.RatingsRepository;
import com.platform.recommendor.app.domain.ports.out.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AddRatingsUseCase {
    private final UserRepository userRepository;
    private final RatingsRepository ratingsRepository;
    private final DomainPublisherEvent publisher;
    public AddRatingsUseCase(UserRepository userRepository, RatingsRepository ratingsRepository,  DomainPublisherEvent publisher) {
        this.userRepository = userRepository;
        this.ratingsRepository = ratingsRepository;
        this.publisher = publisher;
    }
    public RatingResponse execute(UserDetails user, RatingRequest ratingRequest) {
        RatingsModel ratingsModel = new RatingsModel();
        ratingsModel.setBookId(ratingRequest.getBookId());
        ratingsModel.setRating(ratingRequest.getRating());
        UserModel userModel = userRepository.getUserByUsername(user.getUsername());
        ratingsModel.setUserId(userModel.getId());
        RatingsModel savedRating = ratingsRepository.saveRating(ratingsModel);
        publisher.publish(new BookRatedEvent(savedRating.getId(), savedRating.getBookId(), savedRating.getUserId(), savedRating.getRating() ));
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setId(savedRating.getId());
        ratingResponse.setBookId(savedRating.getBookId());
        ratingResponse.setUserId(savedRating.getUserId());
        ratingResponse.setRating(savedRating.getRating());
        return ratingResponse;
    }

}
