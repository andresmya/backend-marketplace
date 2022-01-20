package com.andresmya.backendmarketplace.exception;

public class NotFoundException extends Exception{

    public NotFoundException(String resource){
        super("Following resource was not found: " + resource);
    }
}
