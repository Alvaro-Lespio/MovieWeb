package org.example.movieweb.exceptions;

public class UserUpdateFailed extends RuntimeException{
    public UserUpdateFailed(String message) {
        super(message);
    }
}
