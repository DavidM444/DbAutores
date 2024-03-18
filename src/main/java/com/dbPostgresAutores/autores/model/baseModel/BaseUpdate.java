package com.dbPostgresAutores.autores.model.baseModel;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@MappedSuperclass
public class BaseUpdate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_update")
    private LocalDate lastUpdate;



}
