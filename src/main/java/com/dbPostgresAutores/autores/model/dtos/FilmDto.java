package com.dbPostgresAutores.autores.model.dtos;

import com.dbPostgresAutores.autores.model.Language;

import java.time.Year;

public record FilmDto (
        String title, String description, Year year, Integer languaje,
        short rentalDuration, Double rentalRate, short length, Integer replaceCost,
        String rating, String spFeatures, String fulltext, Integer category, Integer actor
){
}
