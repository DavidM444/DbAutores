package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.*;
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
import org.springframework.test.web.servlet.result.RequestResultMatchers;
import org.springframework.util.Assert;

import java.io.Serial;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//order test conf
@SpringBootTest
@AutoConfigureMockMvc
public class TestAddress {

    final String url = "/api/v1/hello/address";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CityRepository cityRepository;

    //creacion de objetos
    private Country country;
    private City city;


    @BeforeEach
    void setup(){
        System.out.println("Inicializacion objeto a traves de dto");
        country = new Country("Colombia");
        city = new City("Bogota", country);

    }

    @Test
    @Order(2)
    public void saveCityTest(){
        City cityResponse = new City("Bogota",country);
        cityResponse.setId(1);
        Mockito.when(cityRepository.save(city)).thenReturn(cityResponse);

        Assertions.assertEquals(city.getCity(),cityResponse.getCity());
        Assertions.assertEquals(city.getCountryId(), cityResponse.getCountryId());
    }
    @Test
    @Order(1)
    public void saveAddressTest() throws Exception {

        AddressDto addressDto = new AddressDto("Sakila Drive",null, "Alberta",
                 1,"45200","23323");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDto))).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();

        Address address = objectMapper.readValue(result.getResponse().getContentAsString(), Address.class);
        Assertions.assertNotNull(address);
        Assertions.assertEquals(addressDto.postalCode(),address.getPostalCode());
        Assertions.assertNull(address.getAddress2());
        Assertions.assertEquals(address.getAddress(), addressDto.address());
    }







}
