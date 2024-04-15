package com.dbPostgresAutores.autores;

import com.dbPostgresAutores.autores.model.Language;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelLanguageTest {
    final String url = "/api/v1/hello/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Mock
    private CountryRepository countryRepository;

    @Test
    public void dataLanguageTesPost () throws Exception {
        Language language = new Language();
        language.setName("Indian");
        String lang = om.writeValueAsString(language);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url+"language")
                .content(lang).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Language response = om.readValue(resultContent, Language.class);
        Assertions.assertEquals(language.getName(),response.getName());

    }

    @Test
    public void testSaveCountry() throws Exception{
        Country countryRequest = new Country();
        countryRequest.setCountry("Bogota");
        countryRequest.setLastUpdate(LocalDate.of(2024,1,1));

        Country country = new Country();
        country.setId(1);
        country.setCountry("Bogota");
        country.setLastUpdate(LocalDate.of(2024,1,1));
        String send = om.writeValueAsString(country);

        //ver uso de mock y mockbean ya que puede causar errrores.
        Mockito.when(countryRepository.save(countryRequest)).thenReturn(country);

        var result =mockMvc.perform(MockMvcRequestBuilders.post(url+"country").content(send)
                       .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));

        Country country1 = om.readValue(result.andReturn().getResponse().getContentAsString(), Country.class);
        Assertions.assertEquals(country1.getCountry(),country.getCountry());

    }
}
