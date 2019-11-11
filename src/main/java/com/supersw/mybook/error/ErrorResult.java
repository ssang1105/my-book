package com.supersw.mybook.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResult {

    public static final ErrorResult DEFAULT = new ErrorResult("unknown exception occurred", null, null);

    private final String message;
    private final String userMessage;

    @JsonIgnore
    private Throwable cause;

    public static ErrorResult of(@NonNull String userMessage, @NonNull Throwable cause) {
        return new ErrorResult(cause.getMessage(), userMessage, cause);
    }
}
