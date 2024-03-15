package org.example.movieweb.controllers;

import org.example.movieweb.DTO.MovieNameDTO;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.DTO.UserSimplifiedlDTO;
import org.example.movieweb.models.User;
import org.example.movieweb.services.user.IUserService;
import org.example.movieweb.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    //Inyeccion de dependecia del service
    IUserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Crear un usuario
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUsers(user),HttpStatus.OK);
    }

    //listar usuarios
    @GetMapping("/user")
    public ResponseEntity<List<UserSimplifiedlDTO>> listUser(){
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
    @PostMapping("/add-to-movie-list/{userId}/movie/")
    public ResponseEntity<String> addToMovieList(@PathVariable Long userId, @RequestParam("name") String movieId){
        return new ResponseEntity<>(userService.addToListMovie(userId,movieId),HttpStatus.OK);
    }

    //Mostrar biblioteca de peliculas
    @GetMapping("/movie/{userId}")
    public ResponseEntity<Set<MovieNameDTO>> movieList(@PathVariable Long userId){
        return new ResponseEntity<>(userService.movieList(userId),HttpStatus.OK);
    }
}
