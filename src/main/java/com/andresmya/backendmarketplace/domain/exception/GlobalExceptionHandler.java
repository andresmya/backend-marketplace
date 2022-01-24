package com.andresmya.backendmarketplace.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            InvalidArgumentException.class,
            AssociatedRecordDeleteException.class
    })
    public GlobalExceptionModel customBadRequestException(Exception exception){
        return GlobalExceptionModel.of(HttpStatus.BAD_REQUEST.value(), exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    public GlobalExceptionModel customNotFoundException(Exception exception){
        return GlobalExceptionModel.of(HttpStatus.NOT_FOUND.value(), exception);
    }
}
