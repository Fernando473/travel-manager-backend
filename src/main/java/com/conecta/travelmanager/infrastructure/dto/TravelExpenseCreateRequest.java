package com.conecta.travelmanager.infrastructure.dto;

import java.time.LocalDate;

public record TravelExpenseCreateRequest(Boolean isNational, String projectName, String tripReason,
                                         LocalDate dateInvitationTrip,
                                         UserClientCreateRequest userClient) {

}


