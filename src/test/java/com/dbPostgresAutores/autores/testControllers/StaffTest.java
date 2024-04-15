package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.StaffRepository;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class StaffTest {

    static final String url = "/api/v1/hello/staff";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StaffRepository staffRepository;

    @MockBean
    private AddressRepository addressRepository;

    private Address address;
    private Staff staff;

    @BeforeEach
    void setUp(){
        City city = new City("Medellin",new Country("Colombia"));
        AddressDto addressDto = new AddressDto("47 MySakila","boyaca","Alberta",
                1,"543333","5765767657");
        address = new Address(addressDto,city);
        address.setId(2);
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(address));
    }

    @Test
    void whenSaveFilmDto_thenReturnStaffObject() throws Exception {
        StaffDto staffDto = new StaffDto("Mike","Hillyer",1,"mike23@gmail.com",
                3,true,"Mike","8scs88cs7dsc8csa8778dc","The Bell");

        staff = new Staff(staffDto,address);
        staff.setId(1);
        Mockito.when(staffRepository.save(Mockito.any())).thenReturn(staff);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(staffDto)
        )).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressId.id").value(2));
    }


}
