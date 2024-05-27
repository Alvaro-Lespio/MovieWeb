package org.example.movieweb.exceptions;

import org.example.movieweb.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Es una anotacion de spring boot que nos permite manejar excepciones de manera centralizada y global
public class ExceptionConfig {
    //Excepcion para identificar que el usuario fallo al crearse
    @ExceptionHandler(UserCreateFaildException.class) //Esta anotación indica que este método manejará las excepciones del tipo UserUpdateFailed
    public ResponseEntity<ExceptionDTO> badCreateUser(UserCreateFaildException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El usuario no fue creado Correctamente ",406);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    //Excepcion para identificar que el usuario fallo al actualizarse
    @ExceptionHandler(UserUpdateFailedException.class)
    public ResponseEntity<ExceptionDTO> badUpdateUser(UserUpdateFailedException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El usuario no se actualizo correctamente",406);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_REQUEST);
    }
    //Excepcion para identificar que el usuario fallo al encontrar el id
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionDTO> idNotFound(IdNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El id no se encuentra",404);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.NOT_FOUND);
    }
    //Excepcion para identificar que el nombre de la pelicula no se encontro
    @ExceptionHandler(MovieNameNotFoundException.class)
    public ResponseEntity<ExceptionDTO> movieNameNotFound(MovieNameNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El nombre de la pelicula no se encuentra ", 404);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.NOT_FOUND);
    }
    //Excepcion para identificar un error interno
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> internalService(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(exceptionDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
