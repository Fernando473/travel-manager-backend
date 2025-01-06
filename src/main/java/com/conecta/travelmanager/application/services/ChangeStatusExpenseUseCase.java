package com.conecta.travelmanager.application.services;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.models.UserClient;

public interface ChangeStatusExpenseUseCase {
    void execute(Long travelExpenseApprovalId, String userClient, ApprovalStatus approvalStatus) throws NotFoundEntityException;
}
