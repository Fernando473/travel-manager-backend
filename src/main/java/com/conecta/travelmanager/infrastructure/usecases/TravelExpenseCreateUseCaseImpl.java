package com.conecta.travelmanager.infrastructure.usecases;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.TravelExpenseCreateUseCase;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.TravelExpenseRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TravelExpenseCreateUseCaseImpl implements TravelExpenseCreateUseCase {
    private final TravelExpenseRepository travelExpenseRepository;
    private final UserClientRepository userClientRepository;

    public TravelExpenseCreateUseCaseImpl(TravelExpenseRepository travelExpenseRepository, UserClientRepository userClientRepository) {
        this.travelExpenseRepository = travelExpenseRepository;
        this.userClientRepository = userClientRepository;
    }

    @Override
    @Transactional
    public TravelExpense execute(TravelExpense travelExpense, UserClient register) throws NotFoundEntityException {
        UserClient userClient = this.userClientRepository.findByMail(register.getEmail()).orElseThrow(() -> new NotFoundEntityException("Entity with thid emailnotfound"));
        travelExpense.setRegisteredUser(userClient);
        return this.travelExpenseRepository.create(travelExpense);

    }
}
