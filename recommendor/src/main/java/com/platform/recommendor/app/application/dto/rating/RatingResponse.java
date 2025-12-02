package com.platform.recommendor.app.application.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingResponse {
    private Long id;
    private Short rating;
    private Long userId;
    private Long bookId;
}
