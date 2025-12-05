package com.platform.recommendor.app.application.dto.recommendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationInfo {
    private Long id;
    private Long userId;
    private Long recommendBookId;
    private java.util.List<Long> recommendedBookIds;

}
