package com.dbPostgresAutores.autores.model.actorEnt;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "actor")
public class Actor extends BaseModel {

}
