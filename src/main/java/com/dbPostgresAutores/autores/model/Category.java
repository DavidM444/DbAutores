package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "category")
public class Category extends BaseUpdate {
    @Column(name = "name")
    private String name;
}

