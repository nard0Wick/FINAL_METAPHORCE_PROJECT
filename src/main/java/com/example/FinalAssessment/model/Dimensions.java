package com.example.FinalAssessment.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Dimensions {
    private double width;
    private double height;
    private double length;
    private double weight;
    private String unit;
    private String weightUnit;
}
