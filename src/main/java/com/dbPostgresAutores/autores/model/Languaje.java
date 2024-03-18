package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "language")
@Entity
public class Languaje extends BaseUpdate {
    @Column(name = "name")
    private String name;
}
