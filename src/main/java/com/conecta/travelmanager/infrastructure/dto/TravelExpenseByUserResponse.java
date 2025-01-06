package com.conecta.travelmanager.infrastructure.dto;

import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import com.conecta.travelmanager.domain.models.TravelExpense;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TravelExpenseByUserResponse(
        TravelExpenseStatus status,
        LocalDateTime registerDate,
        Boolean isNational,
        String projectName,
        String tripReason,
        @Nullable LocalDate dateTrip,
        @Nullable LocalDate invitationTrip,
        String requester,
        @Nullable String approvedBy

) {

    public static TravelExpenseByUserResponse fromEntity(TravelExpense travelExpense) {
        return TravelExpenseByUserResponse.builder()
                .status(travelExpense.getStatus())
                .dateTrip(travelExpense.getDateTrip())
                .invitationTrip(travelExpense.getInvitationTrip())
                .isNational(travelExpense.getIsNational())
                .registerDate(travelExpense.getRegisterDate())
                .projectName(travelExpense.getProjectName())
                .requester(travelExpense.getRegisteredUser().getName())
                .tripReason(travelExpense.getTripReason())
                .approvedBy(travelExpense.getApprovalUser() != null ? travelExpense.getApprovalUser().getName() : null)
                .build();
    }
}
