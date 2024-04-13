package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
