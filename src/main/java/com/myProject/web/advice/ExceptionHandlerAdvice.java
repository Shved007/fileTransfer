package com.myProject.web.advice;


import com.myProject.exception.BadRequestException;
import com.myProject.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

        ErrorResponse errorResponse = ErrorResponse.of(
                HttpStatus.NOT_FOUND,
                webRequest.getDescription(false),
                ex.getMessage()
        );
        log.error("====== [handle][ResourceNotFoundException] response = {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value = { BadRequestException.class })
//    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest webRequest) {
//        ErrorResponse errorResponse = ErrorResponse.of(
//                HttpStatus.BAD_REQUEST,
//                webRequest.getDescription(false),
//                ex.getMessage()
//        );
//        log.error("====== [handle][BadRequestException] response = {}", errorResponse);
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(value = { Exception.class })
//    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception ex, WebRequest webRequest) {
//        ErrorResponse errorResponse = ErrorResponse.of(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                webRequest.getDescription(false),
//                ex.getMessage()
//        );
//        log.error("====== [handle][Exception] response = {}", errorResponse);
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
