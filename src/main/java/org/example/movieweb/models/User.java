package org.example.movieweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//Entidad User
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") //nombre de la tabla en la base de datos
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generamos un valor con la estrategia del tipo identidad
    private Long id;

    private String email;
    private String name;
    private String lastName;
    private String userName;
    private String password;

    @ManyToMany //generamos una relacion de muchos a muchos (muchos usuarios van a tener muchas peliculas)
    @JoinTable(
            name = "user_movies", //Nombre de la tabla intermeda
            joinColumns = @JoinColumn(name = "user_id"), //Nombre del id de user en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "movie_id") //Nombre del id de movie en la tabla intermedia
    )
    private Set<Movie> movies = new HashSet<>(); //Esto hace referencia a que vamos a tener un set de movie pero cada
                                                 //usuario va a tener el suyo propio
}
