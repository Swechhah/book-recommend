package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.book.BookResponse;
import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import com.platform.recommendor.app.infrastucture.repositories.BookJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBooksUseCase {
 private final BookRecommendorRepository bookRecommendorRepository;
    public GetBooksUseCase(BookRecommendorRepository bookRecommendorRepository) {
        this.bookRecommendorRepository = bookRecommendorRepository;
    }
    public Page<BookResponse> getBooksPage(int pageNumber, int pageSize) {
        Page<BookModel> bookPage = bookRecommendorRepository.getBooksPage(pageNumber, pageSize);
        return bookPage.map(book -> {
            BookResponse response = new BookResponse();
            response.setId(book.getId());
            response.setIsbn(book.getIsbn());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setPublisher(book.getPublisher());
            return response;
        });
    }
    public BookResponse getBooksByIsbn(String isbn) {
        BookModel bookModel = bookRecommendorRepository.getBookByISBN(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        BookResponse response = new BookResponse();
        response.setId(bookModel.getId());
        response.setIsbn(bookModel.getIsbn());
        response.setTitle(bookModel.getTitle());
        response.setAuthor(bookModel.getAuthor());
        response.setPublisher(bookModel.getPublisher());
        response.setImage_url(bookModel.getImage_url());
        return response;
    }
    public List<BookResponse> getBooks() {
        List<BookModel> bookModels = bookRecommendorRepository.getAllBooks();
        return bookModels.stream().map(book -> {
            BookResponse response = new BookResponse();
            response.setId(book.getId());
            response.setIsbn(book.getIsbn());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setPublisher(book.getPublisher());
            response.setImage_url(book.getImage_url());
            return response;
        }).toList();
    }
}

