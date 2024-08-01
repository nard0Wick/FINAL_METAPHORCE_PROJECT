package com.example.FinalAssessment.service;

import com.example.FinalAssessment.model.Product;
import com.example.FinalAssessment.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public void addProduct(Product product){
        productRepo.save(product);
    }
    public Optional<List<Product>> getProduct(String name){
        return productRepo.findByName(name);
    }
    public void updateProduct(Product product){
        Product updated = productRepo.findById(product.get_id())
                .map(p -> {
                    p.setFeatures(product.getFeatures());
                    p.setCategories(product.getCategories());
                    p.setComments(product.getComments());
                    return p;
                        }
                ).orElseThrow(()->new RuntimeException());
        productRepo.save(updated);
    }
}
