package com.dbPostgresAutores.autores.model.manage;

import com.dbPostgresAutores.autores.model.baseModel.BaseModel;
import com.dbPostgresAutores.autores.model.dtos.StaffDto;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "staff")
public class Staff extends BaseModel {
    @OneToOne//review
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address addressId;
    @Column(name = "email")
    private String email;

    //review is not a foreing key on table.
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "picture")
    private String picture;


    public Staff(StaffDto staffDto, Address address){
        super(staffDto.firstName(),staffDto.lastName());
        this.addressId = address;
        this.email = staffDto.email();
        this.storeId = staffDto.storeId();
        this.active = staffDto.active();
        this.username = staffDto.username();
        this.password = staffDto.password();
        this.picture = staffDto.picture();
    }

}
