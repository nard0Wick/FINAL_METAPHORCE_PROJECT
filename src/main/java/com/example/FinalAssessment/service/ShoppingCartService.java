package com.example.FinalAssessment.service;

import com.example.FinalAssessment.dto.ShoppingCartDTO;
import com.example.FinalAssessment.model.Product;
import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.model.User;
import com.example.FinalAssessment.model.WishedProduct;
import com.example.FinalAssessment.repository.ShoppingCartRepo;
import com.example.FinalAssessment.repository.UserRepo;
import com.example.FinalAssessment.repository.WishedProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WishedProductRepo wishedProductRepo;

    public void createShoppingCart(String email, ShoppingCartDTO shoppingCartDTO){

        User user = userRepo.findByEmail(email)
                .orElse(null);
        ShoppingCart shoppingCart = user.getCarts().stream()
                .filter(c -> c.getCartReference().equals(shoppingCartDTO.getCartReference()))
                .findFirst()
                .orElse(null);

        if(user!= null && shoppingCart == null){
            shoppingCart = new ShoppingCart(
                    shoppingCartDTO.getCartReference(),
                    shoppingCartDTO.getCartDescription(),
                    shoppingCartDTO.getWishedProducts()
            );

            ShoppingCart finalShoppingCart = shoppingCart;
            shoppingCart.getWishedProducts().forEach(p -> p.setShoppingCart(finalShoppingCart));
            shoppingCart.setUser(user);

            //shoppingCartRepo.save(shoppingCart);
            user.getCarts().add(shoppingCart);
            userRepo.save(user);
        }
    }

    public List<ShoppingCartDTO> getAllCarts(String email){
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Non user related with that email"));
        return user.getCarts().stream().map(c -> new ShoppingCartDTO(
                c.getCartReference(),
                c.getCartDescription(),
                c.getWishedProducts()
        )).collect(Collectors.toList());
    }

    /*public ShoppingCartDTO getCart(String email, String s){
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("something went wrong :("));
        return user.getCarts().stream().filter(c -> c.getCartReference().equals()).findFirst()
        return shoppingCartRepo.findByCartReference(s).map(c -> new ShoppingCartDTO(
                c.getCartReference(),
                c.getCartDescription(),
                c.getCartCreatedAt(),
                c.getCartExpiresAt(),
                c.getWishedProducts()
        )).orElseThrow(()->new RuntimeException("Couldn't find that shopping cart :("));
    }*/

    /*public void updateCart(String email, String reference, ShoppingCartDTO cartDTO){
        User user = userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Non user related with that email"));

        ShoppingCart cart = user.getCarts().stream()
                .filter(c -> c.getCartReference().equals(reference))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Non cart related with that reference :("));

        //cart.getWishedProducts().clear();
        //wishedProductRepo.deleteAll(cart.getWishedProducts());
        cart.getWishedProducts().clear();
        shoppingCartRepo.save(cart);
        wishedProductRepo.deleteAll(cart.getWishedProducts());

        cart.setCartReference(cartDTO.getCartReference());
        cart.setCartDescription(cartDTO.getCartDescription());
        cart.setCartExpiresAt(cartDTO.getCartExpiresAt());
        cart.setWishedProducts(cartDTO.getWishedProducts());
        cart.getWishedProducts().forEach(p -> p.setShoppingCart(cart));

        shoppingCartRepo.save(cart);

    }*/

//    public void updateCart(String email, String reference, ShoppingCartDTO cart){
//        User user = userRepo.findByEmail(email)
//                .orElseThrow(()->new RuntimeException("Non user related with that email"));
//
//        ShoppingCart shoppingCart = user.getCarts().stream()
//                .filter(c -> c.getCartReference().equals(reference))
//                .findFirst()
//                .orElseThrow(()->new RuntimeException("Non cart related with that reference :("));
//
//        shoppingCart.setCartReference(cart.getCartReference());
//        shoppingCart.setCartDescription(cart.getCartDescription());
//        shoppingCart.setCartExpiresAt(cart.getCartExpiresAt());
//        //adding products that really match
//        shoppingCart.setWishedProducts(cart.getWishedProducts());
//        //cleaning before saving
//        wishedProductRepo.deleteAll(shoppingCart.getWishedProducts());
//        shoppingCart.getWishedProducts().forEach(p -> p.setShoppingCart(shoppingCart));
//
//        //getting all different values
//        /*List<WishedProduct> remainder = shoppingCart.getWishedProducts().stream()
//                .filter(s -> cart.getWishedProducts().stream()
//                        .noneMatch(c -> c.equals(s))).collect(Collectors.toList());*/
//
//        //remainder.forEach(r -> System.out.println(r.getProductId()));
//
//
//        shoppingCartRepo.save(shoppingCart);
//        /*ShoppingCart shoppingCart = shoppingCartRepo.findByCartReference(reference)
//                .map(c -> {
//                    c.setCartReference(cart.getCartReference());
//                    c.setCartDescription(cart.getCartDescription());
//                    c.setCartExpiresAt(cart.getCartExpiresAt());
//                    c.setWishedProducts(cart.getWishedProducts());
//                    return c;
//                })
//                .orElseThrow(()->new RuntimeException("Non cart related with that reference :("));*/
//        //return shoppingCartRepo.save(shoppingCart);
//    }

    public void deleteShoppingCart(String email, String reference){
        User user = userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("That email isn't related with any user"));
        ShoppingCart shoppingCart = user.getCarts().stream()
                .filter(c -> c.getCartReference().equals(reference))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Non cart related with that reference :("));

        user.getCarts().remove(shoppingCart);
        wishedProductRepo.deleteAll(shoppingCart.getWishedProducts());
        shoppingCartRepo.delete(shoppingCart);
    }



}
