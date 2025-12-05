package com.platform.recommendor.app.presentation;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.application.usecases.GetBooksUseCase;
import com.platform.recommendor.app.application.usecases.SearchBooksUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final SearchBooksUseCase searchBooksUseCase;
    private final GetBooksUseCase getBooksUseCase;
    public BookController(SearchBooksUseCase searchBooksUseCase, GetBooksUseCase getBooksUseCase) {
        this.searchBooksUseCase = searchBooksUseCase;
        this.getBooksUseCase = getBooksUseCase;
    }
    @GetMapping("/search/{query}")
    public List<BookResponse> searchBooks(@PathVariable String query) {
        return searchBooksUseCase.searchBooks(query);
    }
    @GetMapping("/isbn/{isbn}")
    public BookResponse getBookByIsbn(@PathVariable String isbn) {
        return getBooksUseCase.getBooksByIsbn(isbn);
    }
    @GetMapping
    public List<BookResponse> getBooks() {
        return getBooksUseCase.getBooks();
    }

}
