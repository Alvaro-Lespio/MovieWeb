package org.example.movieweb.exceptions;

import org.example.movieweb.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(UserCreateFaild.class)
    public ResponseEntity<?> badCreateUser(UserCreateFaild e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El usuario no fue creado Correctamente ",404);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

}
