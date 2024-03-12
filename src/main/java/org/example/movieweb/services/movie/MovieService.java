package org.example.movieweb.services.movie;

import org.example.movieweb.DTO.MovieDTO;
import org.example.movieweb.models.Movie;
import org.example.movieweb.repositories.IMovieRepository;
import org.example.movieweb.services.movie.IMovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieService implements IMovieService {
    //Inyeccion del repository
    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAllMovies();
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> {
            return new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross());
        }).collect(Collectors.toList());
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getMovieByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross())
        ).collect(Collectors.toList());
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getMovieByDirector(String director) {
        List<Movie> movies = movieRepository.findByDirector(director);
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross())
        ).collect(Collectors.toList());
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> getMovieByDate(int date) {
        List<Movie> movies = movieRepository.findByDate(date);
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross())
        ).collect(Collectors.toList());

        return movieDTOS;
    }


}