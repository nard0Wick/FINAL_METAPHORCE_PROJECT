package com.example.FinalAssessment.dto;

import com.example.FinalAssessment.model.WishedProduct;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartDTO {
    private String cartReference;
    private String cartExplanation;
    private Date cartExpiresAt;
    private List<WishedProduct> wishedProducts;
}
