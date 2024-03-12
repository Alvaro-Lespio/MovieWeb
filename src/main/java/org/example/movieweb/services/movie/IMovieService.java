package org.example.movieweb.services.movie;

import org.example.movieweb.DTO.MovieDTO;
import org.example.movieweb.models.Movie;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> getAllMovies();
    List<MovieDTO> getMovieByTitle(String title);

    List<MovieDTO> getMovieByDirector(String director);
    List<MovieDTO> getMovieByDate(int date);
}
