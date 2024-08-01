package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product {
    @Id
    private String _id;
    private Long vendorId;
    private Features features;
    private Set<Category> categories;
    private List<Comments> comments;
}
