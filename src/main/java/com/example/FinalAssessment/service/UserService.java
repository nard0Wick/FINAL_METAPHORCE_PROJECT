package com.example.FinalAssessment.service;

import com.example.FinalAssessment.dto.UserDTO;
import com.example.FinalAssessment.model.stDataSource.User;
import com.example.FinalAssessment.repository.stDataSource.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                    u.getMasterKey().setPassword(usr.getMasterKey().getPassword());
                    //u.setMasterKey(usr.getMasterKey());
                    u.setLocation(usr.getLocation().stream().map(l -> {l.setUser(u); return l;}).collect(Collectors.toSet()));
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
        //user.getLocation().stream().map(l -> {l.setUser(usr); return l;});
        return userRepo.save(user);
    }
}
