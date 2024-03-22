package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.place.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
