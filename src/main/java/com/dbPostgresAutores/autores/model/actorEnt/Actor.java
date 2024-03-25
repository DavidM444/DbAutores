package com.dbPostgresAutores.autores.model.actorEnt;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor extends BaseModel {
}
