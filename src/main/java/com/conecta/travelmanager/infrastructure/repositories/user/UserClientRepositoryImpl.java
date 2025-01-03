package com.conecta.travelmanager.infrastructure.repositories.user;

import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserClientRepositoryImpl implements UserClientRepository {

    private final JpaUserClientRepository jpaUserClientRepository;

    public UserClientRepositoryImpl(JpaUserClientRepository jpaUserClientRepository) {
        this.jpaUserClientRepository = jpaUserClientRepository;
    }

    @Override
    public Optional<UserClient> findById(long userClientId) {
        return this.jpaUserClientRepository.findById(userClientId);
    }

    @Override
    public UserClient create(UserClient userClient) {
        return this.jpaUserClientRepository.save(userClient);
    }
}
