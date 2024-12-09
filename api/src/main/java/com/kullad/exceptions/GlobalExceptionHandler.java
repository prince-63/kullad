package com.kullad.exceptions;

import com.kullad.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception ex, WebRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.CONFLICT
        );
    }
}
