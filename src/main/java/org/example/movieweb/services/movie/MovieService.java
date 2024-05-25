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
    //Inyeccion de dependecia del repository
    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    //Listar todas las peliculas
    public List<MovieDTO> getAllMovies() {
        //Buscamos todas las peliculas cargadas en la base de datos mediante el metodo del repository y lo que retorna
        // lo almacena en una variable del tipo List, luego recorremos esa lista y la convertimos en un dto,
        // el retorno se lo asignamos a otra variable del tipo movie dto y lo retornamos.

        List<Movie> movies = movieRepository.findAllMovies();
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> {
            return new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross());
        }).collect(Collectors.toList());
        return movieDTOS;
    }
    //Listar todas las peliculas por titulo
    @Override
    public List<MovieDTO> getMovieByTitle(String title) {
        //Buscamos una pelicula cargada en la base de datos por titulo que nos llega por parametro,luego de eso llamamos
        //a la funcion creada en esta clase que lo que hace dicha funcion es convertir en movieDTO una lista de objetos movie
        //almacenamos ese valor que retorna la funcion en una variable llamada movieDTOS y la retornamos
        List<Movie> movies = movieRepository.findByTitle(title);
        System.out.println(movies.size());
        List<MovieDTO> movieDTOS = returnMoviesDto(movies);
        return movieDTOS;
    }
    //Listar todas las peliculas por director
    @Override
    public List<MovieDTO> getMovieByDirector(String director) {
        //Buscamos una pelicula cargada en la base de datos por director que nos llega por parametro,luego de eso llamamos
        //a la funcion creada en esta clase que lo que hace dicha funcion es convertir en movieDTO una lista de objetos movie
        //almacenamos ese valor que retorna la funcion en una variable llamada movieDTOS y la retornamos
        List<Movie> movies = movieRepository.findByDirector(director);
        List<MovieDTO> moviesDTOS = returnMoviesDto(movies);
        return moviesDTOS;
    }
    //Listar todas las peliculas por fecha
    @Override
    public List<MovieDTO> getMovieByDate(int date) {
        //Buscamos una pelicula cargada en la base de datos por fecha que nos llega por parametro,luego de eso llamamos
        //a la funcion creada en esta clase que lo que hace dicha funcion es convertir en movieDTO una lista de objetos movie
        //almacenamos ese valor que retorna la funcion en una variable llamada movieDTOS y la retornamos
        List<Movie> movies = movieRepository.findByDate(date);
        List<MovieDTO> movieDTOS = returnMoviesDto(movies);
        return movieDTOS;
    }

    //Creamos una funcion propia de la clase para no tener codigo repetido que se encarga de converitr una movie en una
    //movieDTO
    private List<MovieDTO> returnMoviesDto(List<Movie> movies){
        List<MovieDTO> movieDTOS = movies.stream().map(movie -> new MovieDTO(movie.getPoster_Link(), movie.getSeries_Title(), movie.getReleased_Year(), movie.getCertificate(), movie.getRuntime(), movie.getGenre(), movie.getIMDB_Rating(), movie.getOverview(), movie.getMeta_score(), movie.getDirector(), movie.getStar1(), movie.getStar2(), movie.getStar3(), movie.getStar4(), movie.getNo_of_Votes(), movie.getGross())).collect(Collectors.toList());
        return movieDTOS;
    }

}