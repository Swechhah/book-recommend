package com.platform.recommendor.app.infrastucture.adapters;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.RatingsModel;
import com.platform.recommendor.app.domain.model.RecommendationModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.entities.BookEntity;
import com.platform.recommendor.app.infrastucture.entities.RatingsEntity;
import com.platform.recommendor.app.infrastucture.entities.RecommendationEntity;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import com.platform.recommendor.app.infrastucture.entities.UserEntity;
import com.platform.recommendor.app.infrastucture.repositories.BookJpaRepository;
import com.platform.recommendor.app.infrastucture.repositories.RatingsJpaRepository;
import com.platform.recommendor.app.infrastucture.repositories.RecommendationRepository;
import com.platform.recommendor.app.infrastucture.repositories.UserJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRecommendorRepositoryAdapter implements BookRecommendorRepository {
    private final UserJpaRepository userJpaRepository;
    private final BookJpaRepository bookJpaRepository;
    private final EntityMapper entityMapper;
    private final RatingsJpaRepository ratingsJpaRepository;
    private final RecommendationRepository recommendationRepository;

    BookRecommendorRepositoryAdapter(UserJpaRepository userJpaRepository,
                                     EntityMapper entityMapper,
                                     BookJpaRepository bookJpaRepository,
                                     RatingsJpaRepository ratingsJpaRepository,
                                     RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userJpaRepository = userJpaRepository;
        this.entityMapper = entityMapper;
        this.bookJpaRepository = bookJpaRepository;
        this.ratingsJpaRepository = ratingsJpaRepository;
    }
    @Override
    public Optional<List<UserModel>> getAllUsers() {
        return Optional.of(userJpaRepository.findAll().stream().map(entityMapper::toUserModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userJpaRepository.findById(id).map(entityMapper::toUserModel);
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
    public Optional<UserModel> getUserByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(entityMapper::toUserModel);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(entityMapper::toUserModel);
    }

    @Override
    public List<BookModel> getAllBooks() {
        return bookJpaRepository.findAll().stream().map(entityMapper::toBookModel).collect(Collectors.toList());
    }

    @Override
    public Optional<BookModel> getBookById(Long id) {
        return bookJpaRepository.findById(id).map(entityMapper::toBookModel);
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
    public Optional<BookModel> getBookByISBN(String title) {
        return bookJpaRepository.findByIsbn(title).map(entityMapper::toBookModel);
    }



    @Override
    public List<BookModel> searchBooks(String title, String author, String publisher) {
        return bookJpaRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase
                        (title, author, publisher)
                .stream()
                .map(entityMapper::toBookModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingsModel> getAllRatings() {
        return ratingsJpaRepository.findAll().stream().map(entityMapper::toRatingsModel).collect(Collectors.toList());
    }

    @Override
    public Optional<RatingsModel> getRatingById(Long id) {
        return ratingsJpaRepository.findById(id).map(entityMapper::toRatingsModel);
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
    public Optional<RatingsModel> getRatingByUserIdAndBookId(Long userId, Long bookId) {
        return ratingsJpaRepository.findByUserIdAndBookId(userId, bookId).map(entityMapper::toRatingsModel);
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
        RecommendationEntity recommendationEntitySaved = recommendationRepository.save(recommendationEntity);
        return entityMapper.toRecommendationModel(recommendationEntitySaved);
    }

    @Override
    public RecommendationModel getRecommendationById(Long id) {
        return recommendationRepository.findById(id).map(entityMapper::toRecommendationModel).orElse(null);
    }

    @Override
    public List<RecommendationModel> getRecommendatioByUserId(Long userId) {
        return recommendationRepository.findByUserId(userId).stream()
                .map(entityMapper::toRecommendationModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookModel> getBooksPage(int pageNumber, int pageSize) {
        Page<BookEntity> bookEntityPage = bookJpaRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return bookEntityPage.map(entityMapper::toBookModel);
    }
}
