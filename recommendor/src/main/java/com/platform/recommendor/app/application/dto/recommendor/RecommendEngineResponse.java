package com.platform.recommendor.app.application.dto.recommendor;

public class RecommendEngineResponse {
    private String isbn;
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    RecommendEngineResponse(String isbn) {
        this.isbn = isbn;
    }
    RecommendEngineResponse(){}
}
