package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import jakarta.persistence.*;

import java.time.Year;
@Entity
@Table(name = "film")
public class Film extends BaseUpdate {

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private Year releaseYear;

    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language languageId;

    @Column(name = "rental_duration")
    private short rentalDuration;
    @Column(name = "rental_rate")
    private Integer rentalRate;
    @Column(name = "length")
    private short length;
    @Column(name = "replacement_cost")
    private Integer replacementCost;
    @Column(name = "rating")
    private String  rating;
    @Column(name = "especial_features")
    private String especialFeatures;
    @Column(name = "full_text")
    private String fullText;

    public Film(FilmDto filmDto){

    }
}
