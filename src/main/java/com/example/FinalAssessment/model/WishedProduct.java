package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wished_products")
public class WishedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @Column(name = "discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishedProduct that = (WishedProduct) o;
        return id == that.id && Double.compare(pricePerUnit, that.pricePerUnit) == 0 && Double.compare(discount, that.discount) == 0 && quantity == that.quantity && Objects.equals(shoppingCart, that.shoppingCart) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingCart, productId, pricePerUnit, discount, quantity);
    }
}
