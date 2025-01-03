package com.conecta.travelmanager.domain.models;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelExpenseApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelExpenseApprovalId;

    @ManyToOne
    private TravelExpense travelExpense;

    @Column
    private LocalDateTime approvalDate;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

}
