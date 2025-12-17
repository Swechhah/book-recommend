package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.application.dto.rating.RatingRequest;
import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.RecommendationModel;

import java.util.List;

public class DtoMapper {

    BookResponse getBookResponse(BookModel bookModel) {

        BookResponse response = new BookResponse();
        response.setId(bookModel.getId());
        response.setIsbn(bookModel.getIsbn());
        response.setTitle(bookModel.getTitle());
        response.setAuthor(bookModel.getAuthor());
        response.setPublisher(bookModel.getPublisher());
        response.setImageUrl(bookModel.getImageUrl());
        return response;
    }
    RatingsModel toModel(RatingRequest request, Long userId) {
        RatingsModel model = new RatingsModel();
        model.setBookId(request.getBookId());
        model.setRating(request.getRating());
        model.setUserId(userId);
        return model;
    }

    RatingResponse toResponse(RatingsModel model) {
        RatingResponse response = new RatingResponse();
        response.setId(model.getId());
        response.setBookId(model.getBookId());
        response.setUserId(model.getUserId());
        response.setRating(model.getRating());
        return response;
    }
    RecommendationModel toModel(
            Long userId,
            Long baseBookId,
            List<Long> recommendedBookIds
    ) {
        RecommendationModel model = new RecommendationModel();
        model.setUserId(userId);
        model.setRecommendBookId(baseBookId);
        model.setRecommendedBookIds(recommendedBookIds);
        return model;
    }






}
