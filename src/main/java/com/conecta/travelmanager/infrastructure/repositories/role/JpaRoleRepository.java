package com.conecta.travelmanager.infrastructure.repositories.role;

import com.conecta.travelmanager.domain.enums.RoleName;
import com.conecta.travelmanager.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
