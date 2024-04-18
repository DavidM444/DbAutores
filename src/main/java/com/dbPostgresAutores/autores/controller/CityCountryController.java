package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.dtos.CityDto;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/place")
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

    @GetMapping("/countries")
    public ResponseEntity<Page<Country>> getListCountries(
            @PageableDefault(size = 15, page = 2,
                    sort = {"country","id"},direction = Sort.Direction.ASC)
            Pageable pageable){
        Page<Country> country = countryRepository.findAll(pageable);
        return ResponseEntity.ok().body(country);
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> findCountry(@PathVariable("id")String idCountry){
        Country country = countryRepository.findById(Integer.valueOf(idCountry)).orElse(null);
        if (country == null){
            throw new RuntimeException("objeto vacio country");
        }
        return ResponseEntity.ok(country);

    }


}
