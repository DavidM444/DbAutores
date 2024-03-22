package com.dbPostgresAutores.autores.model.place;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.dtos.CityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "city")
public class City extends BaseUpdate {
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", unique = true)
    private Country countryId;

    public Country getCountryId() {
        return countryId;
    }

    public String getCity() {
        return city;
    }

    @Column(name = "city")
    private String city;

    public City(String city, Country country) {
        super();
        this.countryId = country;
        this.city = city;
    }
}
