package com.dbPostgresAutores.autores.model.place;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseUpdate {
    @Column(name = "address")
    private String address;
    @Column(name = "address2")
    private String address2;
    @Column(name = "district")
    private String district;
    @OneToMany
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City cityId;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "phone")
    private String phone;
}
