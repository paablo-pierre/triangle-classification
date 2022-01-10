package com.cocus.triangleclassification.exception;

import com.cocus.triangleclassification.domain.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TriangleClassificationExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleServiceException(ServiceException ex) {
        return buildErrorResponse(ex.getErrorCode(), ex.getErrorMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorResponseDTO> handleBadRequest() {
        return buildErrorResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return buildErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingHeader(MissingRequestHeaderException ex) {
        return buildErrorResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return buildErrorResponse(
                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponseDTO> handleMediaTypeNoAcceptable() {
        return buildErrorResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    private static ResponseEntity<ErrorResponseDTO> buildErrorResponse(String errorCode, String message, HttpStatus status) {
        return new ResponseEntity<>(buildErrorNotification(errorCode, message), new HttpHeaders(), status);
    }
    private static ErrorResponseDTO buildErrorNotification(String errorCode, String errorMessage) {
        return ErrorResponseDTO.builder()
                .errorCode(errorCode)
                .message(errorMessage)
                .build();
    }

}
