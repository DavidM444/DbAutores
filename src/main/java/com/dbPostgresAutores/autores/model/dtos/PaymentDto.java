package com.dbPostgresAutores.autores.model.dtos;

import java.time.LocalDate;

public record PaymentDto(Integer customerId, Integer staffId, Integer rentalId, Double amount, LocalDate paymentDate) {
}
