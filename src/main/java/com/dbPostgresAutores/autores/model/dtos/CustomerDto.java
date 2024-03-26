package com.dbPostgresAutores.autores.model.dtos;

import java.time.LocalDate;

public record CustomerDto(String firstName, String lastName, Integer storeId, String email, Integer addressId,
                          Boolean activebool, LocalDate createDate, short active) {
}
