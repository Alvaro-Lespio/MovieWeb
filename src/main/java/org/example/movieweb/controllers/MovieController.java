package org.example.movieweb.controllers;

import org.example.movieweb.DTO.MovieDTO;
import org.example.movieweb.services.movie.IMovieService;
import org.example.movieweb.services.movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    //Inyeccion de dependencia del service
    IMovieService movieService;
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    //Traer todas las peliculas
    @GetMapping("/AllMovies")
    public ResponseEntity<List<MovieDTO>> allMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }
    //Traer las peliculas relacionadas con los titulos que ingrese el usuario,
    // si el usuario ingresa star le van a aparecer todas las peliculas de star wars
    @GetMapping("/Title/{title}")
    public ResponseEntity<List<MovieDTO>> movieTitle(@PathVariable String title){
        return new ResponseEntity<>(movieService.getMovieByTitle(title),HttpStatus.OK);
    }
    //Traer todas las peliculas de un director
    @GetMapping("/Director/{director}")
    public ResponseEntity<List<MovieDTO>> movieDirector(@PathVariable String director){
        return new ResponseEntity<>(movieService.getMovieByDirector(director),HttpStatus.OK);
    }
    @GetMapping("/Date/{date}")
    public ResponseEntity<List<MovieDTO>> movieDate(@PathVariable int date){
        return new ResponseEntity<>(movieService.getMovieByDate(date),HttpStatus.OK);
    }
}
