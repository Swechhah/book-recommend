package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationInfo;
import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import com.platform.recommendor.app.infrastucture.ports.RecommendationClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRecommendationsUseCase {
    private final RecommendationClient recommendationClient;
    private final BookRecommendorRepository repository;

    public GetRecommendationsUseCase(RecommendationClient recommendationClient, BookRecommendorRepository repository) {
        this.recommendationClient = recommendationClient;
        this.repository = repository;
    }

    public RecommendationResponse execute(UserDetails user, Long bookId, int topN) {
        String isbn = repository.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"))
                .getIsbn();
        RecommendationResponse response = recommendationClient.getRecommendations(isbn, topN);
        RecommendationModel recommendations = new RecommendationModel();
        if(repository.getUserByUsername(user.getUsername()).isEmpty()) {
            throw new IllegalArgumentException("Username not found");
        }
        UserModel userModel = repository.getUserByUsername(user.getUsername()).get();
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
        repository.saveRecommendations(recommendations);
        return response;

    }

    public Long getBookIdByIsbn(String isbn) {
        return repository.getBookByISBN(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"))
                .getId();
    }

    public List<RecommendationInfo> getUserRecommendations(UserDetails user) {
        List<RecommendationModel> recommendationModels = repository.getRecommendationByUser(user);
        return recommendationModels.stream().map(recommendationModel -> {
            RecommendationInfo info = new RecommendationInfo();
            info.setRecommendBookId(recommendationModel.getRecommendBookId());
            info.setRecommendedBookIds(recommendationModel.getRecommendedBookIds());
            return info;
        }).toList();
    }
}
