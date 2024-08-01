package com.example.FinalAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Features {
    private String name;
    private Price price;
    private int stock;
    private boolean availability;
    private Set<Pictures> pictures;
    private Dimensions dimensions;
}
