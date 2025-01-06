package com.conecta.travelmanager.infrastructure.dto;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import lombok.Builder;

@Builder
public record TravelExpenseApproveFindAllResponse(
        Long travelExpenseApprovalId,
        ApprovalStatus status,

        TravelExpenseByUserResponse travelExpenseByUserResponse

) {
    public static TravelExpenseApproveFindAllResponse fromEntity(TravelExpenseApproval travelExpenseApproval){
        return TravelExpenseApproveFindAllResponse.builder()
                .travelExpenseApprovalId(travelExpenseApproval.getTravelExpenseApprovalId())
                .status(travelExpenseApproval.getApprovalStatus())
                .travelExpenseByUserResponse(TravelExpenseByUserResponse.fromEntity(travelExpenseApproval.getTravelExpense()))
                .build();
    }
}
