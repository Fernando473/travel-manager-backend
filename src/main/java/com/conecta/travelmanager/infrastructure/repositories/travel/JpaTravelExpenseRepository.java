package com.conecta.travelmanager.infrastructure.repositories.travel;

import com.conecta.travelmanager.domain.models.TravelExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTravelExpenseRepository extends JpaRepository<TravelExpense,Long> {
}
