package com.example.FinalAssessment.service;

import com.example.FinalAssessment.dto.ShoppingCartDTO;
import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO){
        ShoppingCart shoppingCart = null;
        if(!shoppingCartRepo.existsByCartReference(shoppingCartDTO.getCartReference())){
            shoppingCart = new ShoppingCart(
                    shoppingCartDTO.getCartReference(),
                    shoppingCartDTO.getCartExplanation(),
                    shoppingCartDTO.getCartExpiresAt(),
                    shoppingCartDTO.getWishedProducts()
            );
            shoppingCartRepo.save(shoppingCart);
        }
        return shoppingCart;
    }


}
