package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.ports.out.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetBooksUseCase {
 private final BookRepository bookRepository;
    public GetBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookResponse> execute(int pageNumber, int pageSize) {

        List<BookModel> books =
                bookRepository.getBooksPage(pageNumber, pageSize);

        return books.stream()
                .map(this::toResponse)
                .toList();
    }

    private BookResponse toResponse(BookModel book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublisher(book.getPublisher());
        response.setImageUrl(book.getImageUrl());
        return response;
    }

}

