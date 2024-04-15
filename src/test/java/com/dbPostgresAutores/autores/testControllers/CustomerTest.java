package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import com.dbPostgresAutores.autores.model.dtos.CustomerDto;
import com.dbPostgresAutores.autores.model.market.Customer;
import com.dbPostgresAutores.autores.model.market.CustomerRepository;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.AddressRepository;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerTest {
    static final String url = "/api/v1/hello/customer";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private CustomerRepository customerRepository;

    private Address address;
    private City city;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        //config manage LocalDate.
        objectMapper.registerModule(new JavaTimeModule());
        City city = new City("Medellin",new Country("Colombia"));
        AddressDto addressDto = new AddressDto("47 MySakila","boyaca","Alberta",
                1,"543333","5765767657");
        address = new Address(addressDto,city);
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(address));
    }

    @Test
    void saveCustomerTest() throws  Exception{
        CustomerDto customerDto = new CustomerDto("Mary", "Smith",1,"mary.smith@sakilacustomer.org",
                1,true, LocalDate.of(2006,2,12), (short) 1);
        Customer customer = new Customer(customerDto,address);
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mary"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressId.district").value("Alberta"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressId.cityId.countryId.country").value("Colombia"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
