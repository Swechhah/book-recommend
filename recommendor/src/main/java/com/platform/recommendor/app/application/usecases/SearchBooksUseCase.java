package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.ports.out.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchBooksUseCase {
    private final BookRepository repository;
    public SearchBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> execute(String query){
        List<BookModel> bookList = repository.searchBooks(query, query, query);
        return bookList.stream()
                .map(book -> {
                    BookResponse response = new BookResponse();
                    response.setId(book.getId());
                    response.setIsbn(book.getIsbn());
                    response.setTitle(book.getTitle());
                    response.setAuthor(book.getAuthor());
                    response.setPublisher(book.getPublisher());
                    response.setImageUrl(book.getImageUrl());
                    return response;
                })
                .toList();

    }
}
