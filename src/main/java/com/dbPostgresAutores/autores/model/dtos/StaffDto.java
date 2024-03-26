package com.dbPostgresAutores.autores.model.dtos;

public record StaffDto(String firstName, String lastName,Integer addressId, String email, Integer storeId,Boolean active,String username,
                       String password, String picture) {
}
