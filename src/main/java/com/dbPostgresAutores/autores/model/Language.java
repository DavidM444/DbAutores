package com.dbPostgresAutores.autores.model;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "language")
@Entity
public class Language extends BaseUpdate {
    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                '}';
    }

    @Column(name = "name")
    private String name;
}
