package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Integer> {
}
