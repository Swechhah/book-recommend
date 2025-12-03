package com.platform.recommendor.app.infrastucture.repositories;

import com.platform.recommendor.app.infrastucture.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
    Page<BookEntity> findAll(Pageable pageable);
    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(
            String title, String author, String publisher);

}
