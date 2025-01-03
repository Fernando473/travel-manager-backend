package com.conecta.travelmanager.infrastructure.repositories.role;

import com.conecta.travelmanager.domain.enums.RoleName;
import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return jpaRoleRepository.findByName(name);
    }

    @Override
    public Role create(Role role) {
        return jpaRoleRepository.save(role);
    }
}
