package com.dbPostgresAutores.autores.model.place;


import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "country")
public class Country extends BaseUpdate {
    @Column(name = "country")
    private String country;

    public Country(){}

}
