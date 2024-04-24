package com.dbPostgresAutores.autores.controller.graphqlController;

import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Controller
public class CityGraphqlController {

    @Autowired
    private CountryRepository countryRepository;

    @QueryMapping
    public List<Country> listCountries(){
        return countryRepository.findAll();
    }

    //@SchemaMapping(typeName = "Query", field = "hello") == @QueryMapping
    @QueryMapping
    private Country findByCountry(@Argument Integer id){
        Optional<Country> country = countryRepository.findById(id);
        return country.orElse(null);

    }

    @QueryMapping
    Flux<Country> fluxCountry(){return Flux.fromIterable(countryRepository.findAll());}

    //Project reactor Mono
    @SchemaMapping(typeName = "Country")
    Mono<Account> account(Country country){
        return Mono.just(new Account(country.getId()));
    }

    @SchemaMapping(typeName = "Query", field = "hello")
    private String hello(){
        return "Hello";
    }

    /*@MutationMapping
    public Country saveCountry(){

    }*/
}

record Account(Integer id){
}

