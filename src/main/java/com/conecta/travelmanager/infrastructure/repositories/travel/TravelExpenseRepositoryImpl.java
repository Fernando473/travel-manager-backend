package com.conecta.travelmanager.infrastructure.repositories.travel;

import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.repositories.TravelExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TravelExpenseRepositoryImpl implements TravelExpenseRepository {

    private final JpaTravelExpenseRepository jpaTravelExpenseRepository;

    public TravelExpenseRepositoryImpl(JpaTravelExpenseRepository jpaTravelExpenseRepository) {
        this.jpaTravelExpenseRepository = jpaTravelExpenseRepository;
    }

    @Override
    public List<TravelExpense> findAll() {
        return this.jpaTravelExpenseRepository.findAll();
    }

    @Override
    public Optional<TravelExpense> findById(long travelExpenseId) {
        return this.jpaTravelExpenseRepository.findById(travelExpenseId);
    }

    @Override
    public List<TravelExpense> findApproved() {
        return null;
    }

    @Override
    public List<TravelExpense> findPending() {
        return null;
    }

    @Override
    public List<TravelExpense> findRejected() {
        return null;
    }

    @Override
    public TravelExpense update(TravelExpense travelExpense) {
        if (travelExpense.getTravelExpenseEntityId() == null || !this.jpaTravelExpenseRepository.existsById(travelExpense.getTravelExpenseEntityId())) {
            throw new EntityNotFoundException("Entity with this id does not exists ");

        }
        return this.jpaTravelExpenseRepository.save(travelExpense);
    }

    @Override
    public TravelExpense create(TravelExpense travelExpense) {
        if (travelExpense.getTravelExpenseEntityId() != null && this.jpaTravelExpenseRepository.existsById(travelExpense.getTravelExpenseEntityId())) {
            throw new IllegalArgumentException("Entity with this id already exists");
        }
        return this.jpaTravelExpenseRepository.save(travelExpense);
    }

}
