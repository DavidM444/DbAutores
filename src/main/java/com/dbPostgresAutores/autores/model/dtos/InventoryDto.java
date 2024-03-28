package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.NotNull;

public record InventoryDto(@NotNull(message = "the inventory requires a valid film.") Integer filmId, @NotNull(message = "store is required.") Integer storeId) {
}
