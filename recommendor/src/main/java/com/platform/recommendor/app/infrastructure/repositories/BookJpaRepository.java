package com.platform.recommendor.app.infrastructure.repositories;

import com.platform.recommendor.app.infrastructure.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(
            String title, String author, String publisher);

}
