package org.example.movieweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
//Entidad Movie
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "imdb_top_1000") //Nombre de la tabla en la base de datos
public class Movie {
    private String Poster_Link;

    @Id
    private String Series_Title;

    private int Released_Year;
    private String Certificate;
    private String Runtime;
    private String Genre;
    private Double IMDB_Rating;
    private String Overview;
    private int Meta_score;
    private String Director;
    private String Star1;
    private String Star2;
    private String Star3;
    private String Star4;
    private int No_of_Votes;
    private String Gross;

    @ManyToMany(mappedBy = "movies") //relacion muchos a muchos pero del lado de movie, que va a estar mapeado por el set
                                     //asignado en user.

    private Set<User> users = new HashSet<>();//Esto hace referencia a que vamos a tener un set de usuarios pero cada
                                              //set de movie va a tener el su propio usuario
}
