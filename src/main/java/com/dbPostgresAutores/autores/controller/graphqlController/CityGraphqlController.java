package com.dbPostgresAutores.autores.controller.graphqlController;

import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    //using batch
    @BatchMapping
    Map<Country, Account> account(List<Country> countries){
        System.out.println("llamando cuenta para "+countries.size()+ " countries");
        return countries.stream()
                .collect(Collectors.toMap(country -> country,
                        country -> new Account(country.getId())));
    }

    //Project reactor Mono
   /* @SchemaMapping(typeName = "Country")
    Mono<Account> account(Country country){
        //problem n+1 -> solve it- batch
        return Mono.just(new Account(country.getId()));
    }*/

    @SchemaMapping(typeName = "Query", field = "hello")
    private String hello(){
        return "Hello";
    }


    //take de value
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @MutationMapping //== to @SchemaMapping(typeName = "Mutation",  field = "insertCountry")
    Country insertCountry(@Argument String name){
        var id = this.atomicInteger.incrementAndGet(); //take value from last id in db and add 1
        System.out.println("id atomic "+id);
        System.out.println("id atomic 2 "+atomicInteger.getPlain());
        return countryRepository.save(new Country(name));
    }

    /*@MutationMapping
    public Country saveCountry(){

    }*/
}

record Account(Integer id){
}

