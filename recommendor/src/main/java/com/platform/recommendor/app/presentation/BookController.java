package com.platform.recommendor.app.presentation;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.application.usecases.GetBooksByIsbnUseCase;
import com.platform.recommendor.app.application.usecases.GetBooksUseCase;
import com.platform.recommendor.app.application.usecases.SearchBooksUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/book")
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
    public Page<BookResponse> getBookspage(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return getBooksUseCase.execute(pageNumber, pageSize);
    }

}
