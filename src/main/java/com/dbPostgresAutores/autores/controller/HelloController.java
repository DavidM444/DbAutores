package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.Category;
import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.dtos.CityDto;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    public final CountryRepository countryRepository;
    public final CityRepository cityRepository;
    public final CategoryRepository categoryRepository;
    public final FilmRepository filmRepository;
    public final LanguageRepository languageRepository;
    public HelloController(CountryRepository coutryRep, CityRepository city, CategoryRepository categoryRepository, FilmRepository filmRepository, LanguageRepository languageRepository){
        this.countryRepository = coutryRep;
        this.cityRepository = city;
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
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

    @PostMapping("/category")
    private ResponseEntity<Category> saveCategory(@RequestBody Category category){
        System.out.println("save entity "+category);
        Category save = categoryRepository.save(category);
        return ResponseEntity.ok(save);
    }
    @PostMapping("/language")
    private ResponseEntity<Language> saveLanguage(@RequestBody Language languaje){
        System.out.println("save entity "+languaje);
        Language save = languageRepository.save(languaje);
        return ResponseEntity.ok(save);
    }
    @PostMapping("/film")
    private ResponseEntity<String> saveFilm(@RequestBody FilmDto filmDto) {
        System.out.println("citydto "+filmDto);
        Film film = new Film(filmDto);
        System.out.println("film "+ film);
        /*

        Optional<Country> optionalCountry = countryRepository.findById(filmDto.idCountry());
        Country country = optionalCountry.orElse(null);
        if(country!=null){
            city = new City(filmDto.city(), country);
            cityRepository.save(city);
        }else{
            throw new RuntimeException("El objeto Country no es correcto ");
        }*/
        return ResponseEntity.ok("esta es citY: "+filmDto );
    }
}


