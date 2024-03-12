package org.example.movieweb.controllers;

import org.example.movieweb.models.User;
import org.example.movieweb.services.user.IUserService;
import org.example.movieweb.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //Inyeccion de dependecia del service
    IUserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Crear un usuario
    @PostMapping("/Create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUsers(user),HttpStatus.OK);
    }
}
