package com.platform.recommendor.app.domain.ports.out;

import com.platform.recommendor.app.domain.model.BookModel;

import java.util.List;
public interface BookRepository {
    List<BookModel> getAllBooks();
    BookModel getBookById(Long id);
    BookModel saveBook(BookModel book);
    void deleteBookById(Long id);
    BookModel getBookByISBN(String title);
    List<BookModel> getBooksPage(int pageNumber, int pageSize);
    List<BookModel> searchBooks(String title, String author, String publisher);
}
