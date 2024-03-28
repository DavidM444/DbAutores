package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDto(String firstName, String lastName, @NotBlank Integer storeId, @Email String email,
                          @NotBlank(message = "addressId is required") Integer addressId,
                          Boolean activebool, @NotNull LocalDate createDate, short active) {
}
