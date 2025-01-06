package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;

import java.util.List;

public interface FindAllExpensesUseCase {
    List<TravelExpenseApproval> execute();
}
