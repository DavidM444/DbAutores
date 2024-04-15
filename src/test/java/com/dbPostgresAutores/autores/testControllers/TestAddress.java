package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.dbPostgresAutores.autores.services.repository.CityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.Optional;
import static org.mockito.Mockito.when;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//order test conf
@SpringBootTest
@AutoConfigureMockMvc
public class TestAddress {
    static final String url = "/api/v1/hello/address";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private CityRepository cityRepository;

    private Country country;
    private City city;

    @BeforeEach
    void setup(){
        country = new Country("Colombia");
        country.setId(1);
    }

    @Test
    public void saveCityTest(){
        City cityResponse = new City("Bogota",country);
        cityResponse.setId(1);
        city = new City("Bogota", country);
        when(cityRepository.save(city)).thenReturn(cityResponse);

        City city1 = cityRepository.save(city);

        Assertions.assertEquals(city1.getCity(),cityResponse.getCity());
        Assertions.assertEquals(city1.getCountryId(), cityResponse.getCountryId());
    }
    @Test
    public void saveAddressTest() throws Exception {
        city = new City("Bogota",country);
        Mockito.when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        AddressDto addressDto = new AddressDto("47 MySakila","boyaca","Alberta",
                1,"543333","5765767657");

        Address address = new Address(addressDto,city);
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(address);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).content(objectMapper.writeValueAsString(addressDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Address addressResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Address.class);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(addressResponse.getPostalCode(),address.getPostalCode());
        Assertions.assertNotNull(address.getAddress2());
        Assertions.assertEquals(address.getAddress(), addressResponse.getAddress());
        Assertions.assertNotNull(address.getCityId());
        //Assertions.assertNotNull(address.getCityId().getId()); country not mock
    }







}
