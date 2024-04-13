package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
