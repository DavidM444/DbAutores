package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseUpdate {
    @Column(name = "name")
    private String name;


}

