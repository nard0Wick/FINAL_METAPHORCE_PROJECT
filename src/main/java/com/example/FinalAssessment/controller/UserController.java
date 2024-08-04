package com.example.FinalAssessment.controller;

import com.example.FinalAssessment.authentication.AuthenticationRequest;
import com.example.FinalAssessment.authentication.AuthenticationResponse;
import com.example.FinalAssessment.dto.UserDTO;
import com.example.FinalAssessment.model.User;
import com.example.FinalAssessment.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    @Autowired
    private UserService userService;

    /*@PostMapping ("/add")
    public User addUser(@RequestBody UserDTO userdto){

        return userService.createUser(userdto);
    }*/

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserDTO userDTO){
        return  new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<>(userService.authenticateUser(authenticationRequest), HttpStatus.OK);
    }
    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PutMapping("/update_user")
    public User updateUser(@RequestBody UserDTO user){
        return userService.updateUser(user);
    }

}
