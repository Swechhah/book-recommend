package com.platform.recommendor.app.application.dto.book;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long  id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String image_url;


}
