package com.dbPostgresAutores.autores.model.place;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.dtos.AddressDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends BaseUpdate {
    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;
    @Column(name = "district")
    private String district;
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City cityId;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "phone")
    private String phone;

    public Address(AddressDto addressDto, City cityId){
        this.address = addressDto.address();
        this.address2 = addressDto.address2();
        this.district = addressDto.district();
        this.cityId = cityId;
        this.postalCode = addressDto.postalCode();
        this.phone = addressDto.phone();
    }



    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", district='" + district + '\'' +
                ", cityId=" + cityId +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
