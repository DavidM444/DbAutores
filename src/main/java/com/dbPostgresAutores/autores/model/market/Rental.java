package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.manage.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "rental")
public class Rental extends BaseUpdate {
    @Column(name = "rental_date")
    private LocalDate rentalDate;
    @OneToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventoryId;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @ManyToOne //review relation
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staffId;


    public Rental(LocalDate rentalRate, LocalDate returnDate, Inventory inventory, Customer customer, Staff staff) {
        super();
        this.rentalDate = rentalRate;
        this.returnDate = returnDate;
        this.customerId = customer;
        this.inventoryId = inventory;
        this.staffId = staff;
    }
}
