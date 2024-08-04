package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "shoppin_cart")
    private ShoppingCart shoppingCart;
    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    @Column(name = "cost")
    private double cost;
    @Column(name = "tax")
    private double tax;
    @Column(name = "total")
    private double total;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;

    public Order(double cost,
                 double tax,
                 double total,
                 String currency,
                 String status) {

        this.cost = cost;
        this.tax = tax;
        this.total = total;
        this.currency = currency;
        this.status = status;
        this.orderDate = new Date();
    }
}
