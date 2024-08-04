package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.model.Product;
import com.example.FinalAssessment.repository.ProductRepo;
import com.example.FinalAssessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get_product_by_name")
    public List<Product> getAllProductsByName(@RequestParam String name){
        return productService.getProduct(name);
    }
    @PostMapping("/add_product")
    public void createProduct(@RequestBody Product product){
       productService.addProduct(product);
    }
    @PutMapping("/update_product")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }
    @DeleteMapping("/delete_product")
    public void deleteProduct(@RequestParam String id){
        productService.deleteProduct(id);
    }
}
