package com.dbPostgresAutores.autores.testControllers;

import com.dbPostgresAutores.autores.model.dtos.*;
import com.dbPostgresAutores.autores.model.manage.Staff;
import com.dbPostgresAutores.autores.model.market.*;
import com.dbPostgresAutores.autores.model.place.Address;
import com.dbPostgresAutores.autores.model.place.City;
import com.dbPostgresAutores.autores.model.place.Country;
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

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentTest {
    static final String url = "/api/v1/hello/payment";

    private Customer customer;
    private Staff staff;
    private Rental rental;

    @MockBean
    static PaymentRepository paymentRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        City city = new City("Medellin",new Country("Colombia"));
        AddressDto addressDto = new AddressDto("47 MySakila","boyaca","Alberta",
                1,"543333","5765767657");
        Address address = new Address(addressDto,city);

        customer = new Customer(new CustomerDto("Mary", "Smith",1,"mary.smith@sakilacustomer.org",
                1,true, LocalDate.of(2006,2,12), (short) 1),address);

        staff = new Staff(new StaffDto("Mike","Hillyer",1,"mike23@gmail.com",
                3,true,"Mike","8scs88cs7dsc8csa8778dc","The Bell"),address);

        RentalDto rentalDto = new RentalDto(LocalDate.of(2002,11,2),1,1,LocalDate.of(2011,4,30),1);
        rental = new Rental(rentalDto.rentalRate(),rentalDto.returnDate(),new Inventory(),customer,staff);
    }

    @Test
    void savePaymentReturnPayment() throws Exception{
        PaymentDto paymentDto = new PaymentDto(1,1,1,2.33, LocalDate.of(2003,12,31));
        Payment payment = new Payment(customer,staff,rental,paymentDto.amount(),paymentDto.paymentDate());

        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);
        mockMvc.perform(MockMvcRequestBuilders.post(url).content(objectMapper.writeValueAsString(paymentDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId.lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(2.33));
    }
}
