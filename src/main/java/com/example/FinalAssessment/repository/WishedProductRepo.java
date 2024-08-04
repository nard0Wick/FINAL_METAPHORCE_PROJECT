package com.example.FinalAssessment.repository;

import com.example.FinalAssessment.model.WishedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishedProductRepo extends JpaRepository<WishedProduct, Long> {
}
