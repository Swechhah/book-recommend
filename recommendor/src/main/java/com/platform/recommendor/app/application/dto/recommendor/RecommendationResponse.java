package com.platform.recommendor.app.application.dto.recommendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecommendationResponse {
    private String isbn;
    private List<RecommendEngineResponse> recommendations;
}
