package com.platform.recommendor.app.domain.ports.out;
import com.platform.recommendor.app.domain.model.RatingsModel;

import java.util.List;

public interface RatingsRepository {
    List<RatingsModel> getAllRatings();
    RatingsModel getRatingById(Long id);
    RatingsModel saveRating(RatingsModel rating);
    void deleteRatingById(Long id);
    RatingsModel getRatingByUserIdAndBookId(Long userId, Long bookId);
    void deleteAllRatingsByUserId(Long userId);
    void deleteAllRatingsByBookId(Long bookId);
    List<RatingsModel> getAllRatingsByBookId(Long bookId);
}
