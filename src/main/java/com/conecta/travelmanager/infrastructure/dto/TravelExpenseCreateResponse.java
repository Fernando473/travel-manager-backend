package com.conecta.travelmanager.infrastructure.dto;

import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
public record TravelExpenseCreateResponse(
        Long id,
        LocalDateTime registerDate,

        UserClientCreateRequest registerUser,
        TravelExpenseStatus status,
        Boolean isNational,
        String projectName,
        String tripReason,

        @Nullable LocalDate dateTrip,
        @Nullable LocalDate invitationTrip
) {
    public TravelExpenseCreateResponse {
        if (isNational != null) {
            if (isNational && invitationTrip != null) {
                throw new IllegalArgumentException("invitationTrip should be null for national trips");
            }
            if (!isNational && dateTrip != null) {
                throw new IllegalArgumentException("dateTrip should be null for international trips");
            }
        }
    }
}
