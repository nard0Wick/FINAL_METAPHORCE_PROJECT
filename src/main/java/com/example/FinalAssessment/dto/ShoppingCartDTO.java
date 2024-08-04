package com.example.FinalAssessment.dto;

import com.example.FinalAssessment.model.WishedProduct;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartDTO {
    public ShoppingCartDTO(String cartReference, String cartDescription, List<WishedProduct> wishedProducts) {
        this.cartReference = cartReference;
        this.cartDescription = cartDescription;
        this.wishedProducts = wishedProducts;
    }

    /*public ShoppingCartDTO(String cartReference, String cartDescription, Date cartExpiresAt, List<WishedProduct> wishedProducts) {
        this.cartReference = cartReference;
        this.cartDescription = cartDescription;
        this.cartExpiresAt = cartExpiresAt;
        this.wishedProducts = wishedProducts;
    }*/

    private String cartReference;
    private String cartDescription;
    private List<WishedProduct> wishedProducts;

}
