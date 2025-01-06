package com.conecta.travelmanager.infrastructure.repositories.user;

import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<UserClient> findByMail(String email) {
        return this.jpaUserClientRepository.findByEmail(email);
    }

    @Override
    public List<String> findRoleNamesByEmail(String email) {
        return this.jpaUserClientRepository.findRoleNamesByEmail(email);
    }

    @Override
    public Set<Role> findRolesByUser(String email) {
        return this.jpaUserClientRepository.findRolesByEmail(email);
    }
}
