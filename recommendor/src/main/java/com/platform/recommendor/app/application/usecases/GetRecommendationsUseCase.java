package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationInfo;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import com.platform.recommendor.app.domain.events.GeneratedRecommendationsEvent;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.domain.ports.out.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRecommendationsUseCase {
    private final RecommendationClient recommendationClient;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;
    private final DomainPublisherEvent publisher;

    public GetRecommendationsUseCase(RecommendationClient recommendationClient, BookRepository bookRepository, UserRepository userRepository, RecommendationRepository recommendationRepository,  DomainPublisherEvent publisher) {
        this.recommendationClient = recommendationClient;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.recommendationRepository = recommendationRepository;
        this.publisher = publisher;
    }

    public RecommendationResponse execute(UserDetails user, Long bookId, int topN) {
        String isbn = bookRepository.getBookById(bookId)
                .getIsbn();
        RecommendationResponse response = recommendationClient.getRecommendations(isbn, topN);
        RecommendationModel recommendations = new RecommendationModel();
        UserModel userModel = userRepository.getUserByUsername(user.getUsername());
        if(userModel == null) {
            throw new IllegalArgumentException("Username not found");
        }
        Long bookIdFinal = getBookIdByIsbn(isbn);
        Long userId = userModel.getId();
        if(response.getRecommendations().isEmpty()) {
            throw new IllegalArgumentException("Recommendations not found");
        }
        List<Long> recommendedBookIds = response.getRecommendations().stream()
                .map(rec -> getBookIdByIsbn(rec.getIsbn()))
                .toList();

        recommendations.setRecommendedBookIds(recommendedBookIds);
         recommendations.setUserId(userId);
            recommendations.setRecommendBookId(bookIdFinal);
        RecommendationModel savedRecommendations = recommendationRepository.saveRecommendations(recommendations);
        publisher.publish(new GeneratedRecommendationsEvent(savedRecommendations.getUserId(), savedRecommendations.getRecommendBookId(), savedRecommendations.getRecommendedBookIds()));
        return response;

    }

    private Long getBookIdByIsbn(String isbn) {
        return bookRepository.getBookByISBN(isbn)
                .getId();
    }

}
