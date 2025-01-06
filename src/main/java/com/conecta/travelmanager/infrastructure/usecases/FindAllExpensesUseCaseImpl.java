package com.conecta.travelmanager.infrastructure.usecases;

import com.conecta.travelmanager.application.services.FindAllExpensesUseCase;
import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.repositories.TravelExpenseApprovalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FindAllExpensesUseCaseImpl implements FindAllExpensesUseCase {
    private final TravelExpenseApprovalRepository travelExpenseApprovalRepository;

    public FindAllExpensesUseCaseImpl(TravelExpenseApprovalRepository travelExpenseApprovalRepository) {
        this.travelExpenseApprovalRepository = travelExpenseApprovalRepository;
    }

    @Override
    public List<TravelExpenseApproval> execute() {
        return this.travelExpenseApprovalRepository.findByApprovalStatus(ApprovalStatus.PENDING);
    }
}
