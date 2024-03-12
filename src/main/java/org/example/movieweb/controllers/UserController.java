package org.example.movieweb.controllers;

import org.apache.catalina.LifecycleState;
import org.example.movieweb.DTO.UserDTO;
import org.example.movieweb.models.User;
import org.example.movieweb.services.user.IUserService;
import org.example.movieweb.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    //Inyeccion de dependecia del service
    IUserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Crear un usuario
    @PostMapping("/Create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUsers(user),HttpStatus.OK);
    }
    //listar usuarios
    @GetMapping("/ListUsers")
    public ResponseEntity<List<UserDTO>> listUser(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    //Actualizar usuarios
    @PutMapping("/updateUsers")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
