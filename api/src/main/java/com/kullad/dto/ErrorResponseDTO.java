package com.kullad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String apiPath;
    private int errorCode;
    private String errorMessage;
    private LocalDateTime errorTimestamp;
}
