package com.dbPostgresAutores.autores.model.place;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City extends BaseUpdate {
    @OneToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", unique = true, nullable = false)
    private Country countryId;

}
