package com.conecta.travelmanager.infrastructure.dto;

import com.conecta.travelmanager.domain.enums.RoleName;
import jakarta.validation.constraints.Email;

import java.util.List;

public record RegisterClientRequest(String identification, String name, @Email(message = "Invalid email format") String email, List<RoleName> roles,
                                    String password, String rePassword) {
}
