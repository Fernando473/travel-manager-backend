package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.enums.RoleName;
import com.conecta.travelmanager.domain.models.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByName(RoleName name);

    Role create(Role role);
}
