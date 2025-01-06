package com.conecta.travelmanager.infrastructure.usecases;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.TravelExpenseCreateUseCase;
import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.TravelExpenseApprovalRepository;
import com.conecta.travelmanager.domain.repositories.TravelExpenseRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TravelExpenseCreateUseCaseImpl implements TravelExpenseCreateUseCase {
    private final TravelExpenseRepository travelExpenseRepository;
    private final UserClientRepository userClientRepository;
    private final TravelExpenseApprovalRepository travelExpenseApprovalRepository;

    public TravelExpenseCreateUseCaseImpl(TravelExpenseRepository travelExpenseRepository, UserClientRepository userClientRepository, TravelExpenseApprovalRepository travelExpenseApprovalRepository) {
        this.travelExpenseRepository = travelExpenseRepository;
        this.userClientRepository = userClientRepository;
        this.travelExpenseApprovalRepository = travelExpenseApprovalRepository;
    }

    @Override
    @Transactional
    public TravelExpense execute(String username, TravelExpense travelExpense) throws NotFoundEntityException {
        UserClient user = userClientRepository.findByMail(username)
                .orElseThrow(() -> new NotFoundEntityException("Usuario no encontrado"));
        

        travelExpense.setRegisteredUser(user);
        var created = this.travelExpenseRepository.create(travelExpense);
        TravelExpenseApproval travelExpenseApproval = TravelExpenseApproval.builder()
                .approvalStatus(ApprovalStatus.PENDING)
                .travelExpense(created)
                .build();

        this.travelExpenseApprovalRepository.create(travelExpenseApproval);

        return created;
    }
}
