package com.example.btl.payload.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class ApiResponse {
    private HttpStatus httpStatus;
    private String message;
    private String error;
    private Object data;

    public ApiResponse(HttpStatus httpStatus, String message, String error, Object data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.error = error;
        this.data = data;
    }
}
