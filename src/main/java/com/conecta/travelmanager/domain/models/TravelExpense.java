package com.conecta.travelmanager.domain.models;

import com.conecta.travelmanager.domain.enums.TravelExpenseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelExpenseEntityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelExpenseStatus status;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime registerDate;

    @ManyToOne
    private UserClient registeredUser;

    @ManyToOne
    private UserClient approvalUser;

    @Column(nullable = false)
    private Boolean isNational;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String tripReason;

    @Column()
    private LocalDate dateTrip;

    @Column()
    private LocalDate invitationTrip;

}
