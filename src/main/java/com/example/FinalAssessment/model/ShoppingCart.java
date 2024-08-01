package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.EAGER,
               mappedBy = "shoppingCart")
    private List<WishedProduct> wishedProducts;

    /*@OneToMany
    @JoinColumn(name = "wished_product_id")
    private List<WishedProduct> wishedProduct;*/

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cart_reference")
    private String cartReference;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cartCreatedAt;

    @Column(name = "is_stuck")
    private boolean isStuck;

    @Column(name = "expires_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cartExpiresAt;

    @Column(name = "is_selected")
    private boolean isSelected;

}
