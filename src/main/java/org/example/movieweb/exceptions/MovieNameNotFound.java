package org.example.movieweb.exceptions;

public class MovieNameNotFound extends RuntimeException{
    public MovieNameNotFound(String message) {
        super(message);
    }
}
