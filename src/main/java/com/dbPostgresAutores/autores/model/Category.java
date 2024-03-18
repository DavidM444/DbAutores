package com.dbPostgresAutores.autores.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "category")
public class Category {
    @Column(name = "name")
    private String name;

}

