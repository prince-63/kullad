package com.kullad.mapper;

import com.kullad.constants.StatusMessageConstants;
import com.kullad.dto.ErrorResponseDTO;
import com.kullad.dto.ResponseDTO;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class ResponseMapper {
    private ResponseMapper() {
    }

    public static ResponseDTO successResponseMapper(int statusCode, String message, Object data) {
        return ResponseDTO.builder()
                .statusMessage(message)
                .statusCode(statusCode)
                .data(data)
                .build();
    }

    public static ErrorResponseDTO errorResponseMapper(int errorCode, Exception e, WebRequest request) {
        return ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorTimestamp(LocalDateTime.now())
                .errorMessage(StatusMessageConstants.ERROR + " : " + e.getMessage())
                .errorCode(errorCode)
                .build();
    }
}
