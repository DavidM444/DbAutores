package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.manage.Store;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer extends BaseModel {
    @OneToMany
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store_id;
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
    private Integer active;

    }
}


