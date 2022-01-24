package com.andresmya.backendmarketplace.domain.exception;

import java.util.Set;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String argument){
        super("Following argument is invalid: " + argument + ".");
    }

    public InvalidArgumentException(Set<String> argument){
        super("Following arguments are invalid: " + argument.stream().reduce((s, s2) -> s + ", " + s2) + ".");
    }
}
