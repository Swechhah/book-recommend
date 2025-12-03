package com.platform.recommendor.app.domain.model;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BookModel {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String image_url;
}
