package com.conecta.travelmanager.infrastructure.controllers;

import com.conecta.travelmanager.application.exceptions.NotFoundEntityException;
import com.conecta.travelmanager.application.services.ChangeStatusExpenseUseCase;
import com.conecta.travelmanager.application.services.FindAllExpensesUseCase;
import com.conecta.travelmanager.application.services.FindExpenseByUserUseCase;
import com.conecta.travelmanager.application.services.TravelExpenseCreateUseCase;
import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import com.conecta.travelmanager.domain.models.TravelExpense;
import com.conecta.travelmanager.domain.models.TravelExpenseApproval;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.infrastructure.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TravelExpenseController {
    private final TravelExpenseCreateUseCase travelExpenseCreateUseCase;
    private final FindExpenseByUserUseCase findExpenseByUserUseCase;
    private final FindAllExpensesUseCase findAllExpensesUseCase;
    private final ChangeStatusExpenseUseCase changeStatusExpenseUseCase;

    public TravelExpenseController(TravelExpenseCreateUseCase travelExpenseCreateUseCase, FindExpenseByUserUseCase findExpenseByUserUseCase, FindAllExpensesUseCase findAllExpensesUseCase, ChangeStatusExpenseUseCase changeStatusExpenseUseCase) {
        this.travelExpenseCreateUseCase = travelExpenseCreateUseCase;
        this.findExpenseByUserUseCase = findExpenseByUserUseCase;
        this.findAllExpensesUseCase = findAllExpensesUseCase;
        this.changeStatusExpenseUseCase = changeStatusExpenseUseCase;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TravelExpenseCreateRequest travelExpenseCreateRequest) throws NotFoundEntityException {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Username actual: " + username);

            TravelExpense travelExpense = TravelExpense.builder()
                    .isNational(travelExpenseCreateRequest.isNational())
                    .status(TravelExpenseStatus.REGISTERED)
                    .projectName(travelExpenseCreateRequest.projectName())
                    .tripReason(travelExpenseCreateRequest.tripReason())
                    .build();
            if (travelExpense.getIsNational()) {
                travelExpense.setDateTrip(travelExpenseCreateRequest.dateInvitationTrip());
            } else {
                travelExpense.setInvitationTrip(travelExpenseCreateRequest.dateInvitationTrip());
            }
            var result = this.travelExpenseCreateUseCase.execute(username, travelExpense);
            var clientResult = result.getRegisteredUser();
            UserClientCreateRequest clientResponse = UserClientCreateRequest.builder()
                    .email(clientResult.getEmail())
                    .identification(clientResult.getIdentification())
                    .name(clientResult.getName())
                    .build();

            TravelExpenseCreateResponse response = TravelExpenseCreateResponse.builder()
                    .id(result.getTravelExpenseEntityId())
                    .isNational(result.getIsNational())
                    .registerDate(result.getRegisterDate())
                    .projectName(result.getProjectName())
                    .tripReason(result.getTripReason())
                    .status(result.getStatus())
                    .dateTrip(result.getDateTrip())
                    .invitationTrip(result.getInvitationTrip())
                    .registerUser(clientResponse)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/")
    public ResponseEntity<?> findByUser() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            List<TravelExpense> list = this.findExpenseByUserUseCase.execute(username);
            List<TravelExpenseByUserResponse> response = list.stream()
                    .map(TravelExpenseByUserResponse::fromEntity)
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/pending")
    public ResponseEntity<?> findAll() {
        try {
            List<TravelExpenseApproval> list = this.findAllExpensesUseCase.execute();
            List<TravelExpenseApproveFindAllResponse> response = list.stream()
                    .map(TravelExpenseApproveFindAllResponse::fromEntity)
                    .toList();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @RequestBody ChangeStatusRequest approvalStatus) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            this.changeStatusExpenseUseCase.execute(id, username, approvalStatus.status());
            return new ResponseEntity<>(new ErrorResponse("Ok"), HttpStatus.OK);
        } catch (NotFoundEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
