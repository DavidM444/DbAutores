package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.baseModel.BaseUpdate;
import com.dbPostgresAutores.autores.model.manage.Staff;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    @OneToOne //review relation
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staffId;
}
