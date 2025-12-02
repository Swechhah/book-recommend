package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import org.springframework.stereotype.Service;

@Service
public class ManageRatingsUseCase {
    private final BookRecommendorRepository repository;
    public ManageRatingsUseCase(BookRecommendorRepository repository) {
        this.repository = repository;
    }

}
