package com.conecta.travelmanager.infrastructure.repositories.user;

import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface JpaUserClientRepository extends JpaRepository<UserClient,Long> {
    Optional<UserClient> findByEmail(String email);
    @Query("SELECT u.roles FROM UserClient u WHERE u.email = :email")
    Set<Role> findRolesByEmail(String email);
    @Query("SELECT r.name FROM UserClient u JOIN u.roles r WHERE u.email = :email")
    List<String> findRoleNamesByEmail(@Param("email") String email);
}
