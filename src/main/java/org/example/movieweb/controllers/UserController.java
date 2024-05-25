package org.example.movieweb.controllers;

import org.example.movieweb.DTO.MovieNameDTO;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.DTO.UserLoginDto;
import org.example.movieweb.models.User;
import org.example.movieweb.services.user.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {
    //Inyeccion de dependecia meidante la interfaz IUserService
    private IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    //Crear un usuario
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        //Vamos a retornar un mensaje que se le asgina en el service y el estado HTTP OK
        return new ResponseEntity<>(userService.createUsers(user),HttpStatus.OK);
    }

    //listar usuarios
    @GetMapping("/user")
    public ResponseEntity<List<UserLoginDto>> listUser(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    //Actualizar usuarios por id
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(userDTO,id),HttpStatus.OK);
    }

    //Eliminar usuario por id
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    //Agregar Peliculas a la biblioteca
    @PostMapping("/movie-list/{userId}/{movie}")
    public ResponseEntity<String> addToMovieList(@PathVariable Long userId, @PathVariable String movie){
            return new ResponseEntity<>(userService.addToListMovie(userId, movie), HttpStatus.OK);
    }

    //Mostrar biblioteca de peliculas
    @GetMapping("/movie-list/{userId}")
    public ResponseEntity<Set<MovieNameDTO>> movieList(@PathVariable Long userId){
        return new ResponseEntity<>(userService.movieList(userId),HttpStatus.OK);
    }
}
