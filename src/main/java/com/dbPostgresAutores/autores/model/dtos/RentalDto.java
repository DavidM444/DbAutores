package com.dbPostgresAutores.autores.model.dtos;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentalDto(LocalDate rentalRate, @NotNull(message = "inventory shouldn't be null.") Integer inventoryId,
                        @NotNull Integer customerId, LocalDate returnDate,@NotNull Integer staffId) {
}
