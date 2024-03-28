package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDto(@NotBlank(message = "valid address expected.") String address, @NotNull String address2,
                         String district,@NotNull(message = "the name of the city is required") Integer cityId,
                         @NotNull String postalCode, String phone) {
}
