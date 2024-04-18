package com.dbPostgresAutores.autores.controller.graphqlController;

import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityGraphqlController {

    @Autowired
    private CountryRepository countryRepository;

    @QueryMapping
    public List<Country> listCountries(){
        return countryRepository.findAll();
    }
}
