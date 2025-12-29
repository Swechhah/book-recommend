package com.platform.recommendor.app.domain.model;


public class RatingsModel {
    private Long id;
    private Short rating;
    private Long userId;
    private Long bookId;

    public RatingsModel(Long id, Short rating, Long userId, Long bookId) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.bookId = bookId;
    }
    public RatingsModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
