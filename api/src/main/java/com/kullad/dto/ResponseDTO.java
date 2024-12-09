package com.kullad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private int statusCode;
    private String statusMessage;
    private Object data;
}
