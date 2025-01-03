package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserClientRepository {
    Optional<UserClient> findById(long userClientId);
    UserClient create(UserClient userClient);
}
