package com.platform.recommendor.app.infrastructure.adapters;

import com.platform.recommendor.app.application.dto.recommendor.RecommendationResponse;
import com.platform.recommendor.app.domain.ports.out.RecommendationClient;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class RecommendationClientAdapter implements RecommendationClient {
    private final WebClient webClient;

    public RecommendationClientAdapter(
            @Value("${recommend.url.python-microservice}") String baseUrl
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public RecommendationResponse getRecommendations(String isbn, int topN) {

        return webClient.post()
                .uri("/recommend")
                .bodyValue(Map.of("isbn", isbn, "top_n", topN))
                .retrieve()
                .bodyToMono(RecommendationResponse.class)
                .block(); // optional: switch to reactive
    }
}
