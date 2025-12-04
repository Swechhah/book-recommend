package com.platform.recommendor.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationModel {
    private Long id;
    private Long userId;
    private Long recommendBookId;
    private java.util.List<Long> recommendedBookIds;
}
