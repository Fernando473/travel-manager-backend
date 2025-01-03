package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;


public interface UserCreateUseCase {
    UserClient execute(UserClient userClient, Role role) throws NotFoundEntityException;
}
