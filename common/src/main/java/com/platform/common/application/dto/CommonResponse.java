package com.platform.common.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CommonResponse.Builder.class)
public class CommonResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;
    private final Integer errorCode;

    private CommonResponse(Builder<T> builder) {
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
        this.timestamp = builder.timestamp;
        this.errorCode = builder.errorCode;
    }

    // Builder class
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder<T> {
        private boolean success;
        private String message;
        private T data;
        private LocalDateTime timestamp;
        private Integer errorCode;

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder<T> errorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public CommonResponse<T> build() {
            if (timestamp == null) {
                timestamp = LocalDateTime.now(); // auto-set timestamp if missing
            }
            return new CommonResponse<>(this);
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    // Static helper for success response
    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Static helper for error response
    public static <T> CommonResponse<T> error(String message, Integer errorCode) {
        return CommonResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
