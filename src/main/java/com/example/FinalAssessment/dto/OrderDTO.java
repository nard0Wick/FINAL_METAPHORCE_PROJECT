package com.example.FinalAssessment.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private double cost;
    private double tax;
    private double total;
    private String currency;
    private String status;
}
