package com.platform.recommendor.app.application.exception;

public class NoBooksAvailableException extends RuntimeException {
    public NoBooksAvailableException(String message) {
        super(message);
    }
}
