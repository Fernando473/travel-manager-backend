package com.conecta.travelmanager.infrastructure.repositories.user;

import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserClientRepository extends JpaRepository<UserClient,Long> {
}
