package com.dbPostgresAutores.autores;

import com.dbPostgresAutores.autores.model.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelLanguageTest {

    @Autowired
    private MockMvc mockMvc;

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
}
