package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;

public interface TravelExpenseCreateUseCase {
    TravelExpense execute(TravelExpense travelExpense, UserClient register) throws NotFoundEntityException;
}
