package org.example.movieweb.repositories;

import org.example.movieweb.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,String> {
    //Creamos mediante JPQL una consulta personaliazada para que desde la base de datos nos traiga todas las peliculas
    @Query("FROM Movie")
    List<Movie> findAllMovies();

    //Creamos mediante JPQL una consulta personaliazada para que desde la base de datos nos traiga todos los titulos relacionados
    //enviados por el usuario.
    @Query("FROM Movie movie WHERE movie.Series_Title LIKE %:title%")
    List<Movie> findByTitle(@Param("title")String title);

    //Creamos mediante JPQL una consulta personaliazada para que desde la base de datos nos traiga todos los directores relacionados
    //enviados por el usuario.
    @Query("FROM Movie m WHERE m.Director LIKE %:director%")
    List<Movie> findByDirector(@Param("director")String director);

    //Creamos mediante JPQL una consulta personaliazada para que desde la base de datos nos traiga todas las fechas relacionados
    //enviados por el usuario.
    @Query("FROM Movie mo WHERE mo.Released_Year = :date")
    List<Movie> findByDate(int date);

}
