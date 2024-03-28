package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.NotBlank;

public record CityDto(@NotBlank(message = "name city is necessary.") String city,@NotBlank(message = "Country cant be empty") Integer idCountry) {
}
