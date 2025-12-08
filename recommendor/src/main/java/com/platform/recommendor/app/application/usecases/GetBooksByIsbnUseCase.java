package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import org.springframework.stereotype.Service;

@Service
public class GetBooksByIsbnUseCase {
    private final BookRecommendorRepository repository;
    public GetBooksByIsbnUseCase(BookRecommendorRepository repository) {
        this.repository = repository;
    }
    public BookResponse execute(String isbn) {
        BookModel bookModel = repository.getBookByISBN(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        BookResponse response = new BookResponse();
        response.setId(bookModel.getId());
        response.setIsbn(bookModel.getIsbn());
        response.setTitle(bookModel.getTitle());
        response.setAuthor(bookModel.getAuthor());
        response.setPublisher(bookModel.getPublisher());
        response.setImageUrl(bookModel.getImageUrl());
        return response;
    }
}
