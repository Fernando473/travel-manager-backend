package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.domain.models.TravelExpense;

public interface TravelExpenseCreateUseCase {
    TravelExpense execute(String username, TravelExpense travelExpense) throws NotFoundEntityException;
}
