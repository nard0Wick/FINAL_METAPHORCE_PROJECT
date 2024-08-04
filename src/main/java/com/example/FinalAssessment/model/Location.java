package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "is_present")
    private boolean present;
}
