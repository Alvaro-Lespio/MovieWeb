package org.example.movieweb.repositories;

import org.example.movieweb.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMovieRepository extends JpaRepository<Movie,String> {
    @Query("FROM Movie")
    List<Movie> findAllMovies();

    @Query("FROM Movie movie WHERE movie.Series_Title LIKE %:title%")
    List<Movie> findByTitle(@Param("title")String title);

    @Query("FROM Movie m WHERE m.Director LIKE %:director%")
    List<Movie> findByDirector(@Param("director")String director);

    @Query("FROM Movie mo WHERE mo.Released_Year = :date")
    List<Movie> findByDate(int date);

}
