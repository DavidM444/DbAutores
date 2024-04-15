package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.Film;
import com.dbPostgresAutores.autores.model.dtos.CustomerDto;
import com.dbPostgresAutores.autores.model.dtos.RentalDto;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.manage.StaffRepository;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.market.*;
import com.dbPostgresAutores.autores.model.place.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
public class RentalTest {

    static final String url = "/api/v1/hello/rental";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RentalRepository rentalRepository;

    private Inventory inventory;
    private Staff staff;
    private Customer customer;

    @BeforeEach
    void setUp(){
        inventory = new Inventory(new Film(),new Store());
        staff = new Staff(new StaffDto("Mike","Hillyer",1,"mike23@gmail.com",
                3,true,"Mike","8scs88cs7dsc8csa8778dc","The Bell"),new Address());

        customer = new Customer(new CustomerDto("Mary", "Smith",3,"mary.smith@sakilacustomer.org",
                1,true, LocalDate.of(2006,2,12), (short) 1),new Address());

        /*  Rental is mocked.
        Mockito.when(inventoryRepository.findById(Mockito.any())).thenReturn(Optional.of(inventory));
        Mockito.when(staffRepository.findById(Mockito.any())).thenReturn(Optional.of(staff));
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
         */
    }

    @Test
    void saveRental() throws  Exception{
        RentalDto rentalDto = new RentalDto(LocalDate.of(2002,11,2),1,1,LocalDate.of(2011,4,30),1);
        Rental rental = new Rental(rentalDto.rentalRate(),rentalDto.returnDate(),inventory,customer,staff);
        Mockito.when(rentalRepository.save(Mockito.any())).thenReturn(rental);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(rentalDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId.storeId").value(3));

    }

}
