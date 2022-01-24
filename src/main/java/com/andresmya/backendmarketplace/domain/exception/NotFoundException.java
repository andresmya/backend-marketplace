package com.andresmya.backendmarketplace.domain.exception;

public class NotFoundException extends Exception{

    public NotFoundException(String resource){
        super("Following resource was not found: " + resource);
    }
}
