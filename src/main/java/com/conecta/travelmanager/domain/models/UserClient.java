package com.conecta.travelmanager.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String identification;

    @ManyToMany
    @JoinTable(
            name = "user_client_role",
            joinColumns = @JoinColumn(name = "user_client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "UserClient{" +
                "id=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                // No incluir roles en toString para evitar la inicialización lazy
                '}';
    }
}

