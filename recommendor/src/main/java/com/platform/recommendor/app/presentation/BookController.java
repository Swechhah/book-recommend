package com.platform.recommendor.app.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.application.usecases.GetBooksByIsbnUseCase;
import com.platform.recommendor.app.application.usecases.GetBooksUseCase;
import com.platform.recommendor.app.application.usecases.SearchBooksUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/book")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class BookController {
    private final SearchBooksUseCase searchBooksUseCase;
    private final GetBooksUseCase getBooksUseCase;
    private final GetBooksByIsbnUseCase getBooksByIsbnUseCase;
    public BookController(SearchBooksUseCase searchBooksUseCase, GetBooksUseCase getBooksUseCase
    , GetBooksByIsbnUseCase getBooksByIsbnUseCase) {
        this.searchBooksUseCase = searchBooksUseCase;
        this.getBooksUseCase = getBooksUseCase;
        this.getBooksByIsbnUseCase = getBooksByIsbnUseCase;
    }
    @GetMapping("/search/{query}")
    public List<BookResponse> searchBooks(@PathVariable String query) {
        return searchBooksUseCase.execute(query);
    }
    @GetMapping("/isbn/{isbn}")
    public BookResponse getBookByIsbn(@PathVariable String isbn) {
        return getBooksByIsbnUseCase.execute(isbn);
    }
    @GetMapping
    public List<BookResponse> getBooks(@RequestParam(defaultValue = "0") int pageNumber,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return getBooksUseCase.execute(pageNumber, pageSize);
    }

}
