package com.conecta.travelmanager.infrastructure.repositories.user;

import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserClientRepository extends JpaRepository<UserClient,Long> {
    Optional<UserClient> findByEmail(String email);
}
