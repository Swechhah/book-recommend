package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.ports.out.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class GetBooksByIsbnUseCase {
    private final BookRepository repository;
    public GetBooksByIsbnUseCase(BookRepository repository) {
        this.repository = repository;
    }
    public BookResponse execute(String isbn) {
        BookModel bookModel = repository.getBookByISBN(isbn);
        return getBookResponse(bookModel);
    }

    private BookResponse getBookResponse(BookModel bookModel) {

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
