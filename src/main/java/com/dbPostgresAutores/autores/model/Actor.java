package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "actor")
public class Actor extends BaseUpdate {
    @Column(name = "fist_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
