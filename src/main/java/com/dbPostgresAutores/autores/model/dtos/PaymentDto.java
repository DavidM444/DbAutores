package com.dbPostgresAutores.autores.model.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PaymentDto(@NotNull(message = "customer is required.") Integer customerId,
                         @NotNull(message = "staff is required.") Integer staffId,
                         @NotNull(message = "rental is required") Integer rentalId,
                         @NotNull(message = "amount shouldn't be null") Double amount,
                         LocalDate paymentDate) {
}
