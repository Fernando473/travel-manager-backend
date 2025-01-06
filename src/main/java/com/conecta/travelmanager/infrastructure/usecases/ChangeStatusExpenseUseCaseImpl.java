package com.conecta.travelmanager.infrastructure.usecases;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.ChangeStatusExpenseUseCase;
import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.TravelExpenseApprovalRepository;
import com.conecta.travelmanager.domain.repositories.TravelExpenseRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeStatusExpenseUseCaseImpl implements ChangeStatusExpenseUseCase {
    private final TravelExpenseApprovalRepository travelExpenseApprovalRepository;
    private final TravelExpenseRepository travelExpenseRepository;

    private final UserClientRepository userClientRepository;

    public ChangeStatusExpenseUseCaseImpl(TravelExpenseApprovalRepository travelExpenseApprovalRepository, TravelExpenseRepository travelExpenseRepository, UserClientRepository userClientRepository) {
        this.travelExpenseApprovalRepository = travelExpenseApprovalRepository;
        this.travelExpenseRepository = travelExpenseRepository;
        this.userClientRepository = userClientRepository;
    }

    @Override
    @Transactional
    public void execute(Long travelExpenseApprovalId, String username, ApprovalStatus approvalStatus) throws NotFoundEntityException {
        Optional<TravelExpenseApproval> travelExpenseApprovalOptional = this.travelExpenseApprovalRepository.findById(travelExpenseApprovalId);
        Optional<UserClient> userClientOptional = this.userClientRepository.findByMail(username);
        if (travelExpenseApprovalOptional.isEmpty()) {
            throw new NotFoundEntityException("Travel Expense Approval not found");
        }
        if (userClientOptional.isEmpty()) {
            throw new NotFoundEntityException("User client  not found");
        }
        UserClient userClient = userClientOptional.get();

        TravelExpenseApproval travelExpenseApproval = travelExpenseApprovalOptional.get();

        travelExpenseApproval.setApprovalStatus(approvalStatus);

        TravelExpense travelExpense = travelExpenseApproval.getTravelExpense();

        travelExpense.setApprovalUser(userClient);

        if (approvalStatus == ApprovalStatus.APPROVED) {
            travelExpense.setStatus(TravelExpenseStatus.APPROVED);
        } else if (approvalStatus == ApprovalStatus.REJECTED) {
            travelExpense.setStatus(TravelExpenseStatus.REJECTED);
        }

        this.travelExpenseRepository.update(travelExpense);

        this.travelExpenseApprovalRepository.update(travelExpenseApproval);
    }
}

