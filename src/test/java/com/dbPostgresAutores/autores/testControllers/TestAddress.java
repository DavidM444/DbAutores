package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
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

@SpringBootTest
@AutoConfigureMockMvc
public class TestAddress {

    @Autowired
    private ObjectMapper om;

    final String url = "/api/v1/hello/address";

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
    public void saveCityTest(){
        City cityResponse = new City("Bogota",country);
        cityResponse.setId(1);
        Mockito.when(cityRepository.save(city)).thenReturn(cityResponse);

        Assertions.assertEquals(city.getCity(),cityResponse.getCity());
        Assertions.assertEquals(city.getCountryId(), cityResponse.getCountryId());
    }








}
