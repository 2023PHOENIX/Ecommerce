package com.ecomm.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;   // <-- ADD THIS FIELD

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;   // <-- ensure this exists
}
