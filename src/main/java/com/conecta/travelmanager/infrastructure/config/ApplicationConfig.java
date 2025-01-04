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


@Configuration
public class ApplicationConfig {
    @Autowired
    JwtUtils jwtUtils;


    @Bean
    public CommandLineRunner createDefaultUser(UserCreateUseCase createUserUseCase, UserClientRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByMail("admin@admin.com").isEmpty()) {

                Role adminRole = roleRepository.findByName(RoleName.EXPENSE_APPROVER).orElseGet(() -> {
                    Role newRole = Role.builder()
                            .name(RoleName.EXPENSE_APPROVER)
                            .build();
                    return roleRepository.create(newRole);
                });


                UserClient defaultUserEntity = UserClient.builder()
                        .identification("0504262361")
                        .name("Admin")
                        .email("fernaulin@gmail.com")
                        .password(passwordEncoder.encode("admin123"))
                        .build();

                createUserUseCase.execute(defaultUserEntity, adminRole);
            }
        };
    }

}
