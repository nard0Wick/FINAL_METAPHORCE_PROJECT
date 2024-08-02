package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.dto.ShoppingCartDTO;
import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ShoppingCart postShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        return shoppingCartService.createShoppingCart(shoppingCartDTO);
    }
}
