package org.example.movieweb.exceptions;

public class UserUpdateFailedException extends RuntimeException{
    public UserUpdateFailedException(String message) {
        super(message);
    }
}
