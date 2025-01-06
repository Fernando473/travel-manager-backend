package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserClientRepository {
    Optional<UserClient> findById(long userClientId);

    UserClient create(UserClient userClient);

    @Query("SELECT DISTINCT u FROM UserClient u WHERE u.email = :email")
    Optional<UserClient> findByMail(@Param("email") String email);

    @Query("SELECT r.name FROM UserClient u JOIN u.roles r WHERE u.email = :email")
    List<String> findRoleNamesByEmail(@Param("email") String email);

    Set<Role> findRolesByUser(String email);
}
