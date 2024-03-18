package com.dbPostgresAutores.autores.model.actorEnt;

import com.dbPostgresAutores.autores.model.Film;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "film_actor")
public class FilmActor {

    @OneToOne
    @JoinColumn(name = "film_id" ,referencedColumnName = "id")
    private Film filmId;

    @OneToOne
    @JoinColumn(name = "actor_id" ,referencedColumnName = "id")
    private Actor actorId;

    @Column(name = "lastUpdate")
    private LocalDate last_update;
}
