package com.dbPostgresAutores.autores.controller;

import com.dbPostgresAutores.autores.model.Category;
import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import com.dbPostgresAutores.autores.services.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    public final CategoryRepository categoryRepository;
    public final FilmRepository filmRepository;
    public final LanguageRepository languageRepository;
    public final ActorRepository actorRepository;

    public HelloController(CategoryRepository categoryRepository, FilmRepository filmRepository, LanguageRepository languageRepository, ActorRepository actorRepository){
        this.categoryRepository = categoryRepository;
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
        this.actorRepository = actorRepository;
    }

    @PostMapping("/category")
    private ResponseEntity<Category> saveCategory(@RequestBody Category category){
        System.out.println("save entity "+category);
        Category save = categoryRepository.save(category);
        return ResponseEntity.ok(save);
    }

    //para que jpa pueda guardar el objeto language debe la clase tener sus geters y setteres respectivos.
    // setter para que llegeuq al metodo, y getter para que sea guardado y la respuesta del save no sea {} nula.
    @PostMapping("/language")
    private ResponseEntity<Language> saveLanguage(@RequestBody Language language){
        System.out.println("save entity "+language.toString());
        Language save = languageRepository.save(language);
        return ResponseEntity.ok(save);
    }
    @PostMapping("/film")
    private ResponseEntity<String> saveFilm(@RequestBody FilmDto filmDto) {
        System.out.println("filmdto "+filmDto);

        Optional<Language> optionalLanguage = languageRepository.findById(filmDto.languaje());

        Language language = optionalLanguage.orElse(null);

        Optional<Category> opCategory = categoryRepository.findById(filmDto.category());
        Category category = opCategory.orElse(null);

        Optional<Actor> opActor = actorRepository.findById(filmDto.actor());
        Actor actor = opActor.orElse(null);

        if(language!=null && category!=null){
            Film film = new Film(filmDto, language, category,actor);
            filmRepository.save(film);
        }else{
            throw new RuntimeException("El objeto language no es correcto ");
        }
        return ResponseEntity.ok("Film has been saved.");
    }

    @PostMapping("/actor")
    public String saveActor(@RequestBody Actor actor){
        System.out.println("actor request"+ actor.toString());
        Actor actor1 = new Actor();

        actorRepository.save(actor);
        return "save actor "+ actor.toString();
    }
}


