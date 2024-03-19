package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.manage.Staff;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Stack;

@Entity
@Table(name = "payment")
public class Payment {

    //id not extended
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @OneToOne //review relation
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;
    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staffId;
    @OneToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rentalId;
    @Column(name = "amount")
    //double is primitive, not permited in Collections: instead use Double(wrapper class-> double)
    private Double amount;
    @Column(name = "payment_date")
    private LocalDate paymentDate;

}
