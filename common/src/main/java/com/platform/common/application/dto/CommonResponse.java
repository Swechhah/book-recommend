package com.platform.common.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private boolean success;       // true if request succeeded
    private String message;        // success or error message
    private T data;                // payload (for success)
    private LocalDateTime timestamp;     // when the response is generated
    private Integer errorCode; // optional error code for failures

    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
    public static <T> CommonResponse<T> error(String message, Integer errorCode) {
        return CommonResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build();
    }


}
