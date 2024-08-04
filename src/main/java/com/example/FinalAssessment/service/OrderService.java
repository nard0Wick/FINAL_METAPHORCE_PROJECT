package com.example.FinalAssessment.service;

import com.example.FinalAssessment.dto.OrderDTO;
import com.example.FinalAssessment.model.Order;
import com.example.FinalAssessment.model.ShoppingCart;
import com.example.FinalAssessment.model.User;
import com.example.FinalAssessment.repository.OrderRepo;
import com.example.FinalAssessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;

    public void createOrder(String userEmail, String cartReference, OrderDTO orderDTO){
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(()->new RuntimeException("Non user related with that email"));

        ShoppingCart shoppingCart = user.getCarts().stream()
                .filter(c -> c.getCartReference().equals(cartReference))
                .findFirst()
                .orElse(null);

        Order order = new Order(orderDTO.getCost(),
                orderDTO.getTax(),
                orderDTO.getTotal(),
                orderDTO.getCurrency(),
                orderDTO.getStatus());

        order.setShoppingCart(shoppingCart);
        order.setUser(user);

        user.getOrders().add(order);
        userRepo.save(user);
    }
}
