package com.platform.recommendor.app.infrastucture.ports;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRecommendorRepository {

    Optional<List<UserModel>> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel saveUser(UserModel user);
    void deleteUser(Long id);
    Optional<UserModel> getUserByUsername(String username);
    Optional<UserModel> getUserByEmail(String email);
    List<BookModel> getAllBooks();
    Optional<BookModel> getBookById(Long id);
    BookModel saveBook(BookModel book);
    void deleteBookById(Long id);
    Optional<BookModel> getBookByISBN(String title);
    Page<BookModel> getBooksPage(int pageNumber, int pageSize);
    List<BookModel> searchBooks(String title, String author, String publisher);
    List<RatingsModel> getAllRatings();
    Optional<RatingsModel> getRatingById(Long id);
    RatingsModel saveRating(RatingsModel rating);
    void deleteRatingById(Long id);
    Optional<RatingsModel> getRatingByUserIdAndBookId(Long userId, Long bookId);
    void deleteAllRatingsByUserId(Long userId);
    void deleteAllRatingsByBookId(Long bookId);
    RecommendationModel saveRecommendations(RecommendationModel recommendationModel);
    RecommendationModel getRecommendationById(Long id);
    List<RecommendationModel> getRecommendatioByUser(UserDetails userDetails);
}
