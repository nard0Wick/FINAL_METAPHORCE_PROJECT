package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.dto.ShoppingCartDTO;
import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping_carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add_shopping_cart")
    public void postShoppingCart(@RequestParam String email,
                                 @RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.createShoppingCart(email, shoppingCartDTO);
    }

    @GetMapping("/")
    public List<ShoppingCartDTO> findAll(String email) {

        return shoppingCartService.getAllCarts(email);
    }

    /*@GetMapping("/getCart")
    public ShoppingCartDTO findCart(@RequestParam String s){
        return shoppingCartService.getCart(s);
    }*/

    /*@PutMapping("/updateCart")
    public void updateCart(@RequestParam String email,
            @RequestParam String s,
            @RequestBody ShoppingCartDTO cart) {
        shoppingCartService.updateCart(email, s, cart);
    }*/

    @DeleteMapping("/delete_shopping_cart")
    public void deleteCart(@RequestParam String email, @RequestParam String s) {
        shoppingCartService.deleteShoppingCart(email, s);
    }
}
