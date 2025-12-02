package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchBooksUseCase {
    private final BookRecommendorRepository repository;
    public SearchBooksUseCase(BookRecommendorRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> searchBooks(String query){
        List<BookModel> bookList = repository.searchBooks(query, query, query);
        return bookList.stream()
                .map(book -> {
                    BookResponse response = new BookResponse();
                    response.setId(book.getId());
                    response.setIsbn(book.getIsbn());
                    response.setTitle(book.getTitle());
                    response.setAuthor(book.getAuthor());
                    response.setPublisher(book.getPublisher());
                    return response;
                })
                .toList();

    }
}
