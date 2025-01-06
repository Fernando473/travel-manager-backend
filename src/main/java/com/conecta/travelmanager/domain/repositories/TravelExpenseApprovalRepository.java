package com.conecta.travelmanager.domain.repositories;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;

import java.util.List;
import java.util.Optional;

public interface TravelExpenseApprovalRepository {
    Optional<TravelExpenseApproval> findById(long travelExpenseApprovalId);

    TravelExpenseApproval create(TravelExpenseApproval travelExpenseApproval);

    TravelExpenseApproval update(TravelExpenseApproval travelExpenseApproval);

    List<TravelExpenseApproval> findAll();
    List<TravelExpenseApproval> findByApprovalStatus(ApprovalStatus aprApprovalStatus);
}
