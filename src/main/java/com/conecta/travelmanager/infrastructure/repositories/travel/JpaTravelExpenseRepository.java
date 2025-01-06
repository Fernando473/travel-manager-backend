package com.conecta.travelmanager.infrastructure.repositories.travel;

import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTravelExpenseRepository extends JpaRepository<TravelExpense,Long> {
    List<TravelExpense> findByRegisteredUser(UserClient registeredUser);
}
