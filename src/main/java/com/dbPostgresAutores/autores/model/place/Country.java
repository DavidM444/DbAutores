package com.dbPostgresAutores.autores.model.place;


import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends BaseUpdate {
    @Column(name = "country")
    private String country;
}
