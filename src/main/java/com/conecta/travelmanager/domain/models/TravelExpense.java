package com.conecta.travelmanager.domain.models;

import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelExpense {

    @Id
    private Long travelExpenseEntityId;

    @Enumerated(EnumType.STRING)
    private TravelExpenseStatus status;

    @Column
    private LocalDate registerDate;

    @ManyToOne
    private UserClient registeredUser;

    @ManyToOne
    private UserClient approvalUser;
}
