package com.platform.recommendor.app.infrastructure.adapters;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastructure.entities.BookEntity;
import com.platform.recommendor.app.infrastructure.entities.RatingsEntity;
import com.platform.recommendor.app.infrastructure.entities.RecommendationEntity;
import com.platform.recommendor.app.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    UserEntity toUserEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userModel.getId());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setRole(userModel.getRole());
        return userEntity;
    }
    UserModel toUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setRole(userEntity.getRole());
        return userModel;
    }
    BookEntity toBookEntity(BookModel bookModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookModel.getId());
        bookEntity.setIsbn(bookModel.getIsbn());
        bookEntity.setTitle(bookModel.getTitle());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setPublisher(bookModel.getPublisher());
        bookEntity.setImageUrl(bookModel.getImageUrl());
        return bookEntity;
    }
    BookModel toBookModel(BookEntity bookEntity) {
        BookModel bookModel = new BookModel();
        bookModel.setId(bookEntity.getId());
        bookModel.setIsbn(bookEntity.getIsbn());
        bookModel.setTitle(bookEntity.getTitle());
        bookModel.setAuthor(bookEntity.getAuthor());
        bookModel.setPublisher(bookEntity.getPublisher());
        bookModel.setImageUrl(bookEntity.getImageUrl());
        return bookModel;
    }

    RatingsModel  toRatingsModel(RatingsEntity ratingsEntity) {
        RatingsModel ratingsModel = new RatingsModel();
        ratingsModel.setId(ratingsEntity.getId());
        ratingsModel.setRating(ratingsEntity.getRating());
        ratingsModel.setBookId(ratingsEntity.getBookId());
        ratingsModel.setUserId(ratingsEntity.getUserId());
        return ratingsModel;
    }
    RatingsEntity toRatingsEntity(RatingsModel ratingsModel) {
        RatingsEntity ratingsEntity = new RatingsEntity();
        ratingsEntity.setId(ratingsModel.getId());
        ratingsEntity.setRating(ratingsModel.getRating());
        ratingsEntity.setBookId(ratingsModel.getBookId());
        ratingsEntity.setUserId(ratingsModel.getUserId());
        return ratingsEntity;
    }
    RecommendationEntity toRecommendationEntity(RecommendationModel recommendationModel) {
        RecommendationEntity recommendationEntity = new RecommendationEntity();
        if (recommendationModel.getId() != null) {
            recommendationEntity.setId(recommendationModel.getId());
        }
        recommendationEntity.setUserId(recommendationModel.getUserId());
        recommendationEntity.setRecommendBookId(recommendationModel.getRecommendBookId());
        recommendationEntity.setRecommendedBookIds(recommendationModel.getRecommendedBookIds());
        return recommendationEntity;
    }
    RecommendationModel toRecommendationModel(RecommendationEntity recommendationEntity) {
        RecommendationModel recommendationModel = new RecommendationModel();
        recommendationModel.setId(recommendationEntity.getId());
        recommendationModel.setUserId(recommendationEntity.getUserId());
        recommendationModel.setRecommendBookId(recommendationEntity.getRecommendBookId());
        recommendationModel.setRecommendedBookIds(recommendationEntity.getRecommendedBookIds());
        return recommendationModel;
    }
}
