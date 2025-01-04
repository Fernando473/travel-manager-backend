package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.models.UserClient;

import java.util.Optional;

public interface UserClientRepository {
    Optional<UserClient> findById(long userClientId);

    UserClient create(UserClient userClient);

    Optional<UserClient> findByMail(String email);
}
