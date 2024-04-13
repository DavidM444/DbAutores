package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.controller.HelloController;
import com.dbPostgresAutores.autores.model.Category;
import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.actorEnt.Actor;
import com.dbPostgresAutores.autores.model.dtos.FilmDto;
import com.dbPostgresAutores.autores.services.repository.ActorRepository;
import com.dbPostgresAutores.autores.services.repository.CategoryRepository;
import com.dbPostgresAutores.autores.services.repository.FilmRepository;
import com.dbPostgresAutores.autores.services.repository.LanguageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Year;
import java.util.Optional;


@SpringBootTest  //not using webMvctest -> for service layer(capa).
@AutoConfigureMockMvc
public class FilmTest {
    static final String url= "/api/v1/hello/film";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LanguageRepository languageRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ActorRepository actorRepository;

    @MockBean
    private FilmRepository filmRepository;

    private Film film;
    private Language language;
    private Category category;
    private Actor actor;

    @BeforeEach
    void setUp(){
        film = new Film();
        //mockeo para repositories con findByID
        language = new Language();
        language.setName("Spanish");
        Mockito.when(languageRepository.findById(Mockito.any())).thenReturn(Optional.of(language));

        category = new Category();
        category.setName("action");
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));

        actor = new Actor();
        actor.setFirstName("Antonio");
        actor.setLastName("Lara");
        Mockito.when(actorRepository.findById(Mockito.any())).thenReturn(Optional.of(actor));
    }

    @Test
    void givenFilm_ReturnStringResponse() throws Exception{
        final String response = "Film has been saved.";

        FilmDto filmDto = new FilmDto("Academy Dinosaur",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                Year.of(2007),1, (short) 6,0.99, (short) 86,2,
                "G","{Trailers,'Deleted Scenes'}",
                "'academi':1 'battl':15 'canadian':20 'dinosaur':2 'drama':5 'epic':4 'feminist':8 'mad':11 'must':14 'rocki':21 " +
                        "'scientist':12 'teacher':17",
                1,1);

        //Film Expected Response
        film = new Film(filmDto,language,category,actor);

        Mockito.when(filmRepository.save(Mockito.any())).thenReturn(film);

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(objectMapper.writeValueAsString(filmDto)).
                contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }
}
