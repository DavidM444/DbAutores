package com.dbPostgresAutores.autores.model.manage;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff extends BaseModel {
    @OneToMany//review
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address addressId;
    @Column(name = "email")
    private String email;
    @OneToOne//review
    @JoinColumn(name = "store_id",referencedColumnName = "id")
    private Store storeId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "picture")
    private String picture;

}
