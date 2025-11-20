package com.ecomm.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    @Builder.Default
    private LocalDateTime timestamp=LocalDateTime.now();
    private int status;
    private String message;
    private String path;
    private T data;
}
