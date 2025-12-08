package com.platform.recommendor.app.application.dto.book;

import com.platform.recommendor.app.application.dto.rating.RatingResponse;
import lombok.*;

import java.util.List;

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
    private String imageUrl;
    private List<RatingResponse> ratings;


}
