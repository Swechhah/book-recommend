package com.platform.recommendor.app.domain.model;

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
    private String imageUrl;
}
