package com.example.FinalAssessment.repository;

import com.example.FinalAssessment.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product, String> {
    @Override
    Optional<Product> findById(String s);

    @Query("{'features.name' : ?0}")
    Optional<List<Product>> findByName(String s);
}
