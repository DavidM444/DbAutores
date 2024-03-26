package com.dbPostgresAutores.autores.model.dtos;

import java.time.LocalDate;

public record RentalDto(LocalDate rentalRate, Integer inventoryId, Integer customerId, LocalDate returnDate, Integer staffId) {
}
