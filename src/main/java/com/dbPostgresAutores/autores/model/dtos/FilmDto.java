package com.dbPostgresAutores.autores.model.dtos;

import com.dbPostgresAutores.autores.model.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record FilmDto (
        @NotBlank(message = "title cant be empty") String title, String description,@NotBlank(message = "Year film is necessary") Year year,
        @NotNull Integer languaje,
        short rentalDuration, Double rentalRate, short length, Integer replaceCost,
        String rating, String spFeatures, String fulltext,@NotBlank(message = "category is necessary") Integer category,
        @NotNull Integer actor
){
}
