package com.dbPostgresAutores.autores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryId;
}
