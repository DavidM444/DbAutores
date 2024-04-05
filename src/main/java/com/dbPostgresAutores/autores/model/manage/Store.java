package com.dbPostgresAutores.autores.model.manage;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.dtos.StoreDto;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Store extends BaseUpdate {
    @OneToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "id")
    private Staff managerStaffId;
    @OneToOne //analizar
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address addressId;

    public Store(Address address, Staff manage){
        this.addressId = address;
        this.managerStaffId = manage;
    }
}