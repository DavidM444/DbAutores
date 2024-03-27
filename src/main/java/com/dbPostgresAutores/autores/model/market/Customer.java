package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.dtos.CustomerDto;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends BaseModel {
    //not defined like fk
    //multiple customers can have same store_id
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address addressId;
    @Column(name = "activebool")
    private Boolean activebool;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "active")//impl Enum
    private short active; //1 -> active  0 -> no active

    public Customer(CustomerDto customerDto, Address address){
        super();
        this.storeId = customerDto.storeId();
        this.email = customerDto.email();
        this.addressId = address;
        this.activebool = customerDto.activebool();
        this.createDate = customerDto.createDate();
        this.active = customerDto.active();
    }
    public Customer(){}
}


