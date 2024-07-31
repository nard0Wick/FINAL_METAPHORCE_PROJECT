package com.example.FinalAssessment.model.stDataSource;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "master_keys")
public class MasterKey {
    public MasterKey(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "token_is_allowed")
    private boolean tokenIsAllowed;

    @Column(name = "token_expires_at")
    private Date tokenExpiresAt;
}
