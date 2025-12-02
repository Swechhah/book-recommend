package com.platform.recommendor.app.application.dto.book;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    public Long  id;
    public String title;
    public String author;
    public String publisher;
    public String isbn;
    public String image_url;


}
