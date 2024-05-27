package org.example.movieweb.controllers;

import org.example.movieweb.DTO.MovieDTO;
import org.example.movieweb.services.movie.IMovieService;
import org.example.movieweb.services.movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    //Inyeccion de dependencia del service
    private IMovieService movieService;

    public MovieController(IMovieService movieService){
        this.movieService = movieService;
    }

    //listar todas las peliculas
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> allMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    //listar las peliculas relacionadas con los titulos que ingrese el usuario,
    // si el usuario ingresa star le van a aparecer todas las peliculas de star wars
    @GetMapping("/movies-tittle/{title}")
    public ResponseEntity<List<MovieDTO>> movieTitle(@PathVariable String title){
        return new ResponseEntity<>(movieService.getMovieByTitle(title),HttpStatus.OK);
    }

    //listar todas las peliculas de un director o directores que contengan el nombre recibido por parametro
    @GetMapping("/movies-director/{director}")
    public ResponseEntity<List<MovieDTO>> movieDirector(@PathVariable String director){
        return new ResponseEntity<>(movieService.getMovieByDirector(director),HttpStatus.OK);
    }

    //listar todas las peliculas de un año en especifico.
    @GetMapping("/movies-date/{date}")
    public ResponseEntity<List<MovieDTO>> movieDate(@PathVariable int date){
        return new ResponseEntity<>(movieService.getMovieByDate(date),HttpStatus.OK);
    }
}
