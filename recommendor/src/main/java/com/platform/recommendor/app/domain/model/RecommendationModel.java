package com.platform.recommendor.app.domain.model;
import java.util.List;


public class RecommendationModel {
    private Long id;
    private Long userId;
    private Long recommendBookId;
    private java.util.List<Long> recommendedBookIds;

    public RecommendationModel(Long id, Long userId, Long recommendBookId, List<Long> recommendedBookIds) {
        this.id = id;
        this.userId = userId;
        this.recommendBookId = recommendBookId;
        this.recommendedBookIds = recommendedBookIds;
    }

    public RecommendationModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecommendBookId() {
        return recommendBookId;
    }

    public void setRecommendBookId(Long recommendBookId) {
        this.recommendBookId = recommendBookId;
    }

    public List<Long> getRecommendedBookIds() {
        return recommendedBookIds;
    }

    public void setRecommendedBookIds(List<Long> recommendedBookIds) {
        this.recommendedBookIds = recommendedBookIds;
    }
}
