package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.dtos.CityDto;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    public final CountryRepository countryRepository;
    public final CityRepository cityRepository;
    public HelloController(CountryRepository coutryRep, CityRepository city){
        this.countryRepository = coutryRep;
        this.cityRepository = city;
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


