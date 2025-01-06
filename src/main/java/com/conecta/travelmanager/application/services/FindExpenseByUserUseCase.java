package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;

import java.util.List;

public interface FindExpenseByUserUseCase {
   List<TravelExpense> execute(String username) throws NotFoundEntityException;
}
