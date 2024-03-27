package com.dbPostgresAutores.autores.model.market;

import com.dbPostgresAutores.autores.model.manage.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    //id not extended
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @ManyToOne //review relation
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;
    @ManyToOne
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

    public Payment(){}
    public Payment(Customer customerId, Staff staffId, Rental rentalId, Double amount, LocalDate paymentDate ){
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
