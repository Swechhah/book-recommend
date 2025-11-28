package com.platform.recommendor.app.infrastucture.adapters;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.entities.BookEntity;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import com.platform.recommendor.app.infrastucture.entities.UserEntity;
import com.platform.recommendor.app.infrastucture.repositories.BookJpaRepository;
import com.platform.recommendor.app.infrastucture.repositories.UserJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.Lint;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRecommendorRepositoryAdapter implements BookRecommendorRepository {
    private final UserJpaRepository userJpaRepository;
    private final BookJpaRepository bookJpaRepository;
    private final EntityMapper entityMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private static final int BATCH_SIZE = 1000;

    BookRecommendorRepositoryAdapter(UserJpaRepository userJpaRepository,  EntityMapper entityMapper,  BookJpaRepository bookJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.entityMapper = entityMapper;
        this.bookJpaRepository = bookJpaRepository;
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
    public void saveAllBooks(List<BookModel> books) {

        int count = 0;

        for (BookModel model : books) {

            saveSingle(model);   // each call has its own transaction

            if (++count % BATCH_SIZE == 0) {
                System.out.println("Imported: " + count);
            }
        }
    }

    @Transactional   // VERY IMPORTANT
    public void saveSingle(BookModel model) {

        BookEntity entity = entityMapper.toBookEntity(model);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();
    }
}
