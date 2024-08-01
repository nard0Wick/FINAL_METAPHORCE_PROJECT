package com.example.FinalAssessment.service;

import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    public void createShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepo.save(shoppingCart);
    }

    public ShoppingCart 
    public void updateShoppingCart(ShoppingCart shoppingCart){

    }
    public
}
