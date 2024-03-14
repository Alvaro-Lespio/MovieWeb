package org.example.movieweb.exceptions;

import org.example.movieweb.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    //Excepcion para identificar que el usuario fallo al crearse
    @ExceptionHandler(UserCreateFaild.class)
    public ResponseEntity<ExceptionDTO> badCreateUser(UserCreateFaild e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El usuario no fue creado Correctamente ",406);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    //Excepcion para identificar que el usuario fallo al actualizarse
    @ExceptionHandler(UserUpdateFailed.class)
    public ResponseEntity<ExceptionDTO> badUpdateUser(UserUpdateFailed e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El usuario no se actualizo correctamente",406);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_REQUEST);
    }
    //Excepcion para identificar que el usuario fallo al encontrar el id
    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<ExceptionDTO> idNotFound(IdNotFound e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("El id no se encuentra",404);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.NOT_FOUND);
    }
    //Excepcion para identificar que el nombre de la pelicula no se encontro
    @ExceptionHandler(MovieNameNotFound.class)
    public ResponseEntity<ExceptionDTO> movieNameNotFound(MovieNameNotFound e){
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
