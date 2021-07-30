package com.myProject.web.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;

    private LocalDateTime timestamp;

    @JsonProperty("request_uri")
    private String requestURI;

    private HttpStatus status;

    private ErrorResponse(final HttpStatus status, final String requestURI, final String message) {
        this.status = status;
        this.requestURI = requestURI;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse of(final HttpStatus status, final String requestURI, final String message) {
        return new ErrorResponse(status, requestURI, message);
    }
}
