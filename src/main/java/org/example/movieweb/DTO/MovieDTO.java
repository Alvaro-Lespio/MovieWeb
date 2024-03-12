package org.example.movieweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class MovieDTO {
    private String Poster_Link;
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
}
