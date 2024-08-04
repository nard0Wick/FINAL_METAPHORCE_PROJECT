package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.dto.OrderDTO;
import com.example.FinalAssessment.model.Order;
import com.example.FinalAssessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add_order")
    public void addOrder(@RequestParam String email,
                         @RequestParam String reference,
                         @RequestBody OrderDTO orderDTO){
        orderService.createOrder(email, reference, orderDTO);
    }
}
