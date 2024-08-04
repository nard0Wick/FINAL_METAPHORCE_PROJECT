package com.example.FinalAssessment.repository;

import com.example.FinalAssessment.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    boolean existsByCartReference(String s);
    Optional<ShoppingCart> findByCartReference(String s);
}
