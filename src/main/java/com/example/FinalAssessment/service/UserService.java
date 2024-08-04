package com.example.FinalAssessment.service;

import com.example.FinalAssessment.authentication.AuthenticationRequest;
import com.example.FinalAssessment.authentication.AuthenticationResponse;
import com.example.FinalAssessment.config.JwtService;
import com.example.FinalAssessment.dto.UserDTO;
import com.example.FinalAssessment.model.User;
import com.example.FinalAssessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepo userRepo,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registerUser(UserDTO userDTO) {
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            throw new KeyAlreadyExistsException("This email \'" + userDTO.getEmail() + "\' is already in use.");
        }

        User user = new User(
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getTelephoneNumber()
        );
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        ));
        User user = userRepo.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()->new IllegalArgumentException("Couldn't find \'"
                        +authenticationRequest.getEmail()
                        +"\' related to any user."));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User createUser(UserDTO userDTO) {
        User user = null;
        if (!userRepo.existsByEmail(userDTO.getEmail())) {
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

    public User updateUser(UserDTO usr) {
        User user = userRepo.findByEmail(usr.getEmail())
                .map(u -> {
                    u.setPassword(usr.getPassword());
                    //u.setMasterKey(usr.getMasterKey());
                    u.setLocation(usr.getLocations().stream().map(l -> {
                        l.setUser(u);
                        return l;
                    }).collect(Collectors.toSet()));
                    u.setName(usr.getName());
                    u.setLastName(usr.getLastName());
                    //u.setEmail(usr.getEmail());
                    u.setTelephoneNumber(usr.getTelephoneNumber());
                    //u.setAccountCreatedAt(usr.getAccountCreatedAt());
                    u.setActive(true);
                    //u.setEmail(usr.getEmail());
                    return u;
                })
                .orElseThrow(() -> new RuntimeException());
        //user.getLocation().stream().map(l -> {l.setUser(usr); return l;});
        return userRepo.save(user);
    }

    public User getUser(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("that email isn't related with any user!"));
    }

    public void deleteUser(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("that email isn't related with any user!"));

        user.setActive(false);
    }
}
