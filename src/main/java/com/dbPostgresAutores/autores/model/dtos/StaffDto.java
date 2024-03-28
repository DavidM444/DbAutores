package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StaffDto(String firstName, String lastName, @NotNull Integer addressId,
                       @NotBlank @Email(message = "invalid email address.") String email,@NotNull Integer storeId, Boolean active,
                       String username,@NotBlank String password, String picture) {
}
