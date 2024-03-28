package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.NotNull;

public record StoreDto (@NotNull(message = "manager staff is required.") Integer managerStaffId,
                        @NotNull(message = "address shouldn't be null.") Integer addressId){
}
