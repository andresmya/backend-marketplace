package com.andresmya.backendmarketplace.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalExceptionModel {
    private String message;
    private Integer status;

    public static GlobalExceptionModel of(Integer statusCode, Exception e){
        return GlobalExceptionModel.builder()
                .message(e.getMessage())
                .status(statusCode)
                .build();
    }
}
