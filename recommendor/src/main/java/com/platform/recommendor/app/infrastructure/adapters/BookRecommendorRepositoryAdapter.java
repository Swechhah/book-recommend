package com.platform.recommendor.app.infrastructure.adapters;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.domain.ports.out.BookRepository;
import com.platform.recommendor.app.domain.ports.out.RatingsRepository;
import com.platform.recommendor.app.domain.ports.out.RecommendationRepository;
import com.platform.recommendor.app.domain.ports.out.UserRepository;
import com.platform.recommendor.app.infrastructure.entities.BookEntity;
import com.platform.recommendor.app.infrastructure.entities.RatingsEntity;
import com.platform.recommendor.app.infrastructure.entities.RecommendationEntity;
import com.platform.recommendor.app.infrastructure.entities.UserEntity;
import com.platform.recommendor.app.infrastructure.repositories.BookJpaRepository;
import com.platform.recommendor.app.infrastructure.repositories.RatingsJpaRepository;
import com.platform.recommendor.app.infrastructure.repositories.RecommendationJpaRepository;
import com.platform.recommendor.app.infrastructure.repositories.UserJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookRecommendorRepositoryAdapter implements UserRepository, BookRepository, RatingsRepository, RecommendationRepository {
    private final UserJpaRepository userJpaRepository;
    private final BookJpaRepository bookJpaRepository;
    private final EntityMapper entityMapper;
    private final RatingsJpaRepository ratingsJpaRepository;
    private final RecommendationJpaRepository recommendationJpaRepository;

    BookRecommendorRepositoryAdapter(UserJpaRepository userJpaRepository,
                                        EntityMapper entityMapper,
                                        BookJpaRepository bookJpaRepository,
                                        RatingsJpaRepository ratingsJpaRepository,
                                        RecommendationJpaRepository recommendationJpaRepository) {
        this.recommendationJpaRepository = recommendationJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.entityMapper = entityMapper;
        this.bookJpaRepository = bookJpaRepository;
        this.ratingsJpaRepository = ratingsJpaRepository;
    }
    @Override
    public List<UserModel> getAllUsers() {
        return userJpaRepository.findAll().stream().map(entityMapper::toUserModel).toList();
    }

    @Override
    public UserModel getUserById(Long id) {
        return userJpaRepository.findById(id).map(entityMapper::toUserModel).get();
    }

    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity  userEntity = entityMapper.toUserEntity(user);
        UserEntity userEntitySaved = userJpaRepository.save(userEntity);
        return entityMapper.toUserModel(userEntitySaved);
    }

    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(entityMapper::toUserModel).get();
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(entityMapper::toUserModel).get();
    }

    @Override
    public List<BookModel> getAllBooks() {
        return bookJpaRepository.findAll().stream().map(entityMapper::toBookModel).toList();
    }

    @Override
    public BookModel getBookById(Long id) {
        return bookJpaRepository.findById(id).map(entityMapper::toBookModel).get();
    }

    @Override
    public BookModel saveBook(BookModel book) {
        BookEntity bookEntity = entityMapper.toBookEntity(book);
        BookEntity bookEntitySaved = bookJpaRepository.save(bookEntity);
        return entityMapper.toBookModel(bookEntitySaved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookJpaRepository.deleteById(id);

    }

    @Override
    public BookModel getBookByISBN(String title) {
        return bookJpaRepository.findByIsbn(title).map(entityMapper::toBookModel).get();
    }



    @Override
    public List<BookModel> searchBooks(String title, String author, String publisher) {
        return bookJpaRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase
                        (title, author, publisher)
                .stream()
                .map(entityMapper::toBookModel)
                .toList();
    }

    @Override
    public List<RatingsModel> getAllRatings() {
        return ratingsJpaRepository.findAll().stream().map(entityMapper::toRatingsModel).toList();
    }

    @Override
    public RatingsModel getRatingById(Long id) {
        return ratingsJpaRepository.findById(id).map(entityMapper::toRatingsModel).get();
    }

    @Override
    public RatingsModel saveRating(RatingsModel rating) {
        RatingsEntity ratingsEntity = entityMapper.toRatingsEntity(rating);
        RatingsEntity ratingsEntitySaved = ratingsJpaRepository.save(ratingsEntity);
        return entityMapper.toRatingsModel(ratingsEntitySaved);
    }

    @Override
    public void deleteRatingById(Long id) {
        ratingsJpaRepository.deleteById(id);

    }

    @Override
    public RatingsModel getRatingByUserIdAndBookId(Long userId, Long bookId) {
        return ratingsJpaRepository.findByUserIdAndBookId(userId, bookId).map(entityMapper::toRatingsModel).get();
    }

    @Override
    public void deleteAllRatingsByUserId(Long userId) {
        ratingsJpaRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteAllRatingsByBookId(Long bookId) {
        ratingsJpaRepository.deleteAllByBookId(bookId);
    }

    @Override
    public RecommendationModel saveRecommendations(RecommendationModel recommendationModel) {
        RecommendationEntity recommendationEntity = entityMapper.toRecommendationEntity(recommendationModel);
        RecommendationEntity recommendationEntitySaved = recommendationJpaRepository.save(recommendationEntity);
        return entityMapper.toRecommendationModel(recommendationEntitySaved);
    }

    @Override
    public RecommendationModel getRecommendationById(Long id) {
        return recommendationJpaRepository.findById(id).map(entityMapper::toRecommendationModel).orElse(null);
    }

    @Override
    public List<RecommendationModel> getRecommendationByUser(String username) {
        UserModel userModel = getUserByUsername(username);
        return recommendationJpaRepository.findByUserId(userModel.getId()).stream()
                .map(entityMapper::toRecommendationModel)
                .toList();
    }



    @Override
    public List<BookModel> getBooksPage(int pageNumber, int pageSize) {

        if (pageNumber < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page parameters");
        }

        Page<BookEntity> page =
                bookJpaRepository.findAll(PageRequest.of(pageNumber, pageSize));

        return page.getContent()
                .stream()
                .map(entityMapper::toBookModel)
                .toList();
    }

    @Override
    public List<RatingsModel> getAllRatingsByBookId(Long bookId) {
        List<RatingsEntity> ratingsEntityList = ratingsJpaRepository.findAllByBookId(bookId);
        return ratingsEntityList.stream().map(entityMapper::toRatingsModel).toList();
    }
}
