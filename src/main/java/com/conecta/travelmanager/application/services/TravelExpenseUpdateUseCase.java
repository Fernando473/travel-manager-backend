package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;

public interface TravelExpenseUpdateUseCase {
    TravelExpense execute(TravelExpense travelExpense, UserClient approver);
}
