package com.conecta.travelmanager.infrastructure.usecases;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.FindExpenseByUserUseCase;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.TravelExpenseRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import com.conecta.travelmanager.infrastructure.services.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindExpenseByUserUseCaseImpl implements FindExpenseByUserUseCase {
    private final UserClientRepository userClientRepository;
    private final TravelExpenseRepository travelExpenseRepository;

    public FindExpenseByUserUseCaseImpl( UserClientRepository userClientRepository, TravelExpenseRepository travelExpenseRepository) {
        this.userClientRepository = userClientRepository;
        this.travelExpenseRepository = travelExpenseRepository;
    }

    @Override
    public List<TravelExpense> execute(String username) throws NotFoundEntityException {
        Optional<UserClient> user = this.userClientRepository.findByMail(username);
        if (user.isEmpty()){
            throw new NotFoundEntityException("User not found");
        }
        return this.travelExpenseRepository.findByUserClient(user.get());
    }
}
