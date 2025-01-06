package com.conecta.travelmanager.infrastructure.repositories.travelapproval;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTravelExpenseApprovalRepository extends JpaRepository<TravelExpenseApproval, Long> {
    List<TravelExpenseApproval> findByApprovalStatus(ApprovalStatus approvalStatus);
}
