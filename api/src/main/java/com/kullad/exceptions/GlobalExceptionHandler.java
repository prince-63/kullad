package com.kullad.exceptions;

import com.kullad.constants.StatusCodeConstants;
import com.kullad.constants.StatusMessageConstants;
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
                .errorCode(StatusCodeConstants.INTERNAL_SERVER_ERROR)
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(StatusMessageConstants.ERROR + " : " + ex.getMessage())
                .build();
        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(StatusCodeConstants.NOT_FOUND)
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(StatusMessageConstants.ERROR + " : " + ex.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(StatusCodeConstants.CONFLICT)
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(StatusMessageConstants.ERROR + " : " + ex.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponseDTO, HttpStatus.CONFLICT
        );
    }
}
