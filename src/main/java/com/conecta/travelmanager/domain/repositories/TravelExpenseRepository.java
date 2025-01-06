package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;

import java.util.List;
import java.util.Optional;

public interface TravelExpenseRepository {
    List<TravelExpense> findAll();

    Optional<TravelExpense> findById(long travelExpenseId);

    List<TravelExpense> findApproved();

    List<TravelExpense> findPending();

    List<TravelExpense> findRejected();

    TravelExpense update(TravelExpense travelExpense);

    TravelExpense create(TravelExpense travelExpense);

    List<TravelExpense> findByUserClient(UserClient userClient);
}
