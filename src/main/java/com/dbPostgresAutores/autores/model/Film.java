package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
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
    private Double rentalRate;
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

    @OneToMany
    private List<Category> category = new ArrayList<>();

    @OneToMany
    private List<Actor> actor = new LinkedList<>();

    public Film(FilmDto filmDto, Language language, Category category, Actor actor){
        super();
        this.title = filmDto.title();
        this.description = filmDto.description();
        this.releaseYear =  filmDto.year();
        this.languageId = language;
        this.rentalDuration =  filmDto.rentalDuration();
        this.rentalRate =  filmDto.rentalRate();
        this.length =  filmDto.length();
        this.replacementCost =  filmDto.replaceCost();
        this.rating =  filmDto.rating();
        this.especialFeatures =  filmDto.spFeatures();
        this.fullText =  filmDto.fulltext();
        this.category.add(category);
        this.actor.add(actor);
    }
    public Film(){}
}
