package com.dbPostgresAutores.autores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
@Entity
@Table(name = "film_actor")
public class FilmActor {
    @Column(name = "lastUpdate")
    private LocalDate last_update;
}
