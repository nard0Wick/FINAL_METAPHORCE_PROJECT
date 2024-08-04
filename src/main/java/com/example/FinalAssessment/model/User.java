package com.example.FinalAssessment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    public User(String password, String name, String lastName, String email, String telephoneNumber) {
        //this.masterKey = new MasterKey(password);
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.role = Role.USER;
        this.accountCreatedAt = new Date();
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "key_id")
    private MasterKey masterKey;*/

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Location> location;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<ShoppingCart> carts;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Order> orders;

    @Column(name = "user_name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date accountCreatedAt;

    @Column(name = "is_active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
