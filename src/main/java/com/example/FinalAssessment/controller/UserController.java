package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.dto.UserDTO;
import com.example.FinalAssessment.model.stDataSource.User;
import com.example.FinalAssessment.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/add")
    public User addUser(@RequestBody UserDTO userdto){

        return userService.createUser(userdto);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
