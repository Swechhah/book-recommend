package com.platform.recommendor.app.domain.events;

public class BookRatedEvent {
    private final Long ratingId;
    private final Long userId;
    private final Long bookId;
    private final short rating;


    public BookRatedEvent(
            Long ratingId,
            Long userId,
            Long bookId,
            short rating
    ) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public short getRating() {
        return rating;
    }
}
