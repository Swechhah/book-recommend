package com.platform.recommendor.app.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class RatingsModel {
    private Long id;
    private Short rating;
    private Long userId;
    private Long bookId;
}
