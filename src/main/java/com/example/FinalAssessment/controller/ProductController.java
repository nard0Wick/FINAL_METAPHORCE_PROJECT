package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.model.Product;
import com.example.FinalAssessment.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
       return productRepo.save(product);
    }
}
