package com.example.FinalAssessment.service;

import com.example.FinalAssessment.dto.UserDTO;
import com.example.FinalAssessment.model.MasterKey;
import com.example.FinalAssessment.model.User;
import com.example.FinalAssessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAll(){
        return userRepo.findAll();
    }
    public User createUser(UserDTO userDTO){
        User user = null;
        if(!userRepo.existsByEmail(userDTO.getEmail())){
            user = new User(
                userDTO.getPassword(),
                userDTO.getLastName(),
                userDTO.getTelephoneNumber(),
                userDTO.getEmail(),
                userDTO.getPassword()
            );
            userRepo.save(user);
        }
        return user;

    }

    public User updateUser(User usr){
        User user = userRepo.findByEmail(usr.getEmail())
                .map(u -> {
                    u.setMasterKey(usr.getMasterKey());
                    u.setLocation(usr.getLocation());
                    u.setName(usr.getName());
                    u.setLastName(usr.getLastName());
                    u.setEmail(usr.getEmail());
                    u.setTelephoneNumber(usr.getTelephoneNumber());
                    u.setAccountCreatedAt(usr.getAccountCreatedAt());
                    u.setActive(usr.isActive());
                    u.setEmail(usr.getEmail());
                    return u;
                })
                .orElseThrow(()->new RuntimeException());
        return userRepo.save(user);
    }
}
