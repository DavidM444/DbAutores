package com.dbPostgresAutores.autores;

import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.dtos.CityDto;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelLanguageTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    private Country country() {
        Country country = new Country();
        country.setId(1);
        country.setLastUpdate(LocalDate.of(2024,1,1));
        country.setCountry("Bogota");


        return country;

    }


    @BeforeEach
    void setup(){
        System.out.println("setup beforeEach");

    }



    @Test
    public void data_language_test_post () throws Exception {

        Language language = new Language();
        language.setName("Indian");
        ObjectMapper om = new ObjectMapper();
        String lang = om.writeValueAsString(language);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hello/language")
                .content(lang).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Language response = om.readValue(resultContent, Language.class);
        Assertions.assertEquals(language.getName(),response.getName());

    }

    @Test
    public void test_save_city_country_and_address() throws Exception{
        Country countryRequest = new Country();
        countryRequest.setCountry("Bogota");
        countryRequest.setLastUpdate(LocalDate.of(2024,1,1));

        Country country = country();


        Mockito.when(countryRepository.save(countryRequest)).thenReturn(country);

       mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hello/country").contentType(MediaType.APPLICATION_JSON)
               ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}
