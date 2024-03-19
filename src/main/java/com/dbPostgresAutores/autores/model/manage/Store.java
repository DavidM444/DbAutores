package com.dbPostgresAutores.autores.model.manage;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.place.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "store")
public class Store extends BaseUpdate {
    @OneToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "id")
    private Staff managerStaffId;
    @OneToMany //analizar
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address addressId;
}
