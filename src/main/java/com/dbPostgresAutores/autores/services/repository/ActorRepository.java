package com.dbPostgresAutores.autores.services.repository;

import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
}
