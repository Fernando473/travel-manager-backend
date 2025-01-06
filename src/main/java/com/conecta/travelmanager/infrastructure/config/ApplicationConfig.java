package com.conecta.travelmanager.infrastructure.config;

import com.conecta.travelmanager.application.services.UserCreateUseCase;
import com.conecta.travelmanager.domain.enums.RoleName;
import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.RoleRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import com.conecta.travelmanager.infrastructure.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class ApplicationConfig {
    @Autowired
    JwtUtils jwtUtils;


    @Bean
    public CommandLineRunner createDefaultUser(UserCreateUseCase createUserUseCase, UserClientRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByMail("admin@admin.com").isEmpty()) {

                Role adminRole = roleRepository.findByName(RoleName.EXPENSE_APPROVER)
                        .orElseGet(() -> {
                            Role newRole = Role.builder()
                                    .name(RoleName.EXPENSE_APPROVER)
                                    .build();
                            return roleRepository.create(newRole);
                        });

                Role userRole = roleRepository.findByName(RoleName.EXPENSE_REQUESTER)
                        .orElseGet(() -> {
                            Role newRole = Role.builder()
                                    .name(RoleName.EXPENSE_REQUESTER)
                                    .build();
                            return roleRepository.create(newRole);
                        });

                UserClient defaultUserEntity = UserClient.builder()
                        .identification("0504262361")
                        .name("Admin")
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("admin123"))
                        .build();

                List<Role> roles = new ArrayList<>();
                roles.add(adminRole);
                roles.add(userRole);

                createUserUseCase.execute(defaultUserEntity, roles);

                System.out.println("Usuario creado con éxito !");
            }

        };
    }

}
