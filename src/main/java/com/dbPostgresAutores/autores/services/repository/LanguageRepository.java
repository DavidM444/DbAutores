package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
