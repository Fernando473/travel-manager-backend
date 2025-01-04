package com.conecta.travelmanager.infrastructure.controllers;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.TravelExpenseCreateUseCase;
import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.infrastructure.dto.TravelExpenseCreateRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("travel-expense")
public class TravelExpenseController {
    private final TravelExpenseCreateUseCase travelExpenseCreateUseCase;

    public TravelExpenseController(TravelExpenseCreateUseCase travelExpenseCreateUseCase) {
        this.travelExpenseCreateUseCase = travelExpenseCreateUseCase;
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public TravelExpense create(@RequestBody TravelExpenseCreateRequest travelExpenseCreateRequest) throws NotFoundEntityException {
        var userRequest = travelExpenseCreateRequest.userClient();
        UserClient userClient = UserClient.builder()
                .name(userRequest.name())
                .identification(userRequest.identification())
                .email(userRequest.email())
                .build();

        TravelExpense travelExpense = TravelExpense.builder()
                .isNational(travelExpenseCreateRequest.isNational())
                .status(TravelExpenseStatus.REGISTERED)
                .projectName(travelExpenseCreateRequest.projectName())
                .tripReason(travelExpenseCreateRequest.tripReason())
                .build();
        if(travelExpense.getIsNational()){
            travelExpense.setDateTrip(travelExpenseCreateRequest.dateInvitationTrip());
        }else{
            travelExpense.setInvitationTrip(travelExpenseCreateRequest.dateInvitationTrip());
        }

        return this.travelExpenseCreateUseCase.execute(travelExpense, userClient);
    }
}
