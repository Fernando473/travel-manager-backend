package com.conecta.travelmanager.infrastructure.usecases;


import com.conecta.travelmanager.application.services.UserCreateUseCase;
import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.RoleRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateUserUseCaseImpl implements UserCreateUseCase {
    private final UserClientRepository userClientRepository;
    private final RoleRepository roleRepository;

    public CreateUserUseCaseImpl(UserClientRepository userClientRepository, RoleRepository roleRepository) {
        this.userClientRepository = userClientRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserClient execute(UserClient userClient, Role role) {
        Role existingRole = roleRepository.findByName(role.getName())
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(role.getName());
                    return roleRepository.create(newRole);
                });

        if (userClient.getRoles() == null) {
            userClient.setRoles(new HashSet<>());
        }

        userClient.getRoles().add(existingRole);

        return userClientRepository.create(userClient);
    }


}
