package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.place.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
