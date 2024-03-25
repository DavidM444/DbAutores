package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
