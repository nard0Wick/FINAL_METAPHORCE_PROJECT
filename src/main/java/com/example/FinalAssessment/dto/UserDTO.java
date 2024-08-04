package com.example.FinalAssessment.dto;

import com.example.FinalAssessment.model.Location;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String telephoneNumber;
    private Set<Location> locations;
}
