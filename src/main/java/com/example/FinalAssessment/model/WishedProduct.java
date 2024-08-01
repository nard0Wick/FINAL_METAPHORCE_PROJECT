package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wished_products")
public class WishedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart;
    //product

    @Column(name = "price_per_unit")
    private double pricePerUnit;
    @Column(name = "discount")
    private double discount;
    @Column(name = "quantity")
    private int quantity;
}
