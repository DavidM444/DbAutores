package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.dtos.CityDto;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CityCountryController {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityCountryController(CityRepository cityRepository, CountryRepository countryRepository){
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @GetMapping
    private ResponseEntity<String> saludo(@RequestParam("id") String id){
        return ResponseEntity.ok("Hola desde request: Este es el param "+id);
    }

    @PostMapping("/country")
    private ResponseEntity<Country> saveCountry(@RequestBody Country country){
        System.out.println("save entity "+country);
        Country save = countryRepository.save(country);
        return ResponseEntity.ok(save);
    }


    @PostMapping("/city")
    private ResponseEntity<String> saveCity(@RequestBody CityDto cityDto) {
        System.out.println("citydto "+cityDto);
        City city;
        Optional<Country> optionalCountry = countryRepository.findById(cityDto.idCountry());
        Country country = optionalCountry.orElse(null);
        if(country!=null){
            city = new City(cityDto.city(), country);
            cityRepository.save(city);
        }else{
            throw new RuntimeException("El objeto Country no es correcto ");
        }
        return ResponseEntity.ok("esta es citY: " );
    }


}
