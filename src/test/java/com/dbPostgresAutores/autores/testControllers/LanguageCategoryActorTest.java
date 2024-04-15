package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.Category;
import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import com.dbPostgresAutores.autores.services.repository.ActorRepository;
import com.dbPostgresAutores.autores.services.repository.CategoryRepository;
import com.dbPostgresAutores.autores.services.repository.LanguageRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
public class LanguageCategoryActorTest {
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public LanguageCategoryActorTest(final LanguageRepository languageRepository,
                                     final CategoryRepository categoryRepository,
                                     final ActorRepository actorRepository) {
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
        this.actorRepository = actorRepository;

    }

    @Test
    void saveLanguageEntity(){
        Language language = new Language();
        language.setName("Spanish");

        Language languageResponse = languageRepository.save(language);
        Assertions.assertNotNull(languageResponse);
        Assertions.assertEquals(languageResponse.getId(),1);
        Assertions.assertEquals(language.getName(),languageResponse.getName());
    }

    @Test
    void saveCategoryEntity(){
        Category category = new Category();
        category.setName("Action");
        Category categoryResponse = categoryRepository.save(category);

        Assertions.assertEquals(category.getId(),categoryResponse.getId());
        Assertions.assertNotNull(categoryResponse);
    }

    @Test
    void saveActorEntity(){
        Actor actor = new Actor();
        actor.setFirstName("Penelope");
        actor.setLastName("Guiness");

        Actor actorResponse = actorRepository.save(actor);
        Assertions.assertNotNull(actorResponse);

        Supplier<String> name = ()-> "Penelope";
        Assertions.assertEquals(actorResponse.getFirstName(), name.get());
    }
}
