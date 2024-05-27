package org.example.movieweb.exceptions;

public class MovieNameNotFoundException extends RuntimeException{
    public MovieNameNotFoundException(String message) {
        super(message);
    }
}
