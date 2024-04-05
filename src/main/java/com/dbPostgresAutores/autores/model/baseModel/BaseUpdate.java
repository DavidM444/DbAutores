package com.dbPostgresAutores.autores.model.baseModel;
import jakarta.persistence.*;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Setter
@MappedSuperclass
public class BaseUpdate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_update")
    private LocalDate lastUpdate = LocalDate.now();

}
