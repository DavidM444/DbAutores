package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.controller.Controller2;
import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.dtos.StoreDto;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.StaffRepository;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.manage.StoreRepository;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.junit.JUnitTestRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

//@WebMvcTest(Controller2.class)//need satisfactory dependencies in constructor controller.
@SpringBootTest
@AutoConfigureMockMvc
public class StoreTest {
    static final String url = "/api/v1/hello/store";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private StaffRepository staffRepository;


    private Address address;
    private Staff staff;

    @BeforeEach
    void setUp(){
        City city = new City("Medellin",new Country("Colombia"));
        AddressDto addressDto = new AddressDto("47 MySakila","boyaca","Alberta",
                1,"543333","5765767657");
        address = new Address(addressDto,city);

        staff = new Staff(new StaffDto("Mike","Hillyer",1,"mike23@gmail.com",
                3,true,"Mike","8scs88cs7dsc8csa8778dc","The Bell"),address);

        Mockito.when(staffRepository.findById(Mockito.any())).thenReturn(Optional.of(staff));
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(address));
        objectMapper = new ObjectMapper();
    }



    @Test
    void saveStore() throws Exception{
        StoreDto storeDto = new StoreDto(1,1);

        Store store = new Store(address,staff);
        Mockito.when(storeRepository.save(Mockito.any())).thenReturn(store);
        mockMvc.perform(MockMvcRequestBuilders.post(url).content(objectMapper.writeValueAsString(storeDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Staff object has been saved successfully."));

    }
}
