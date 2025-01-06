package com.conecta.travelmanager.infrastructure.dto;

import com.conecta.travelmanager.domain.enums.ApprovalStatus;

public record ChangeStatusRequest(ApprovalStatus status) {
}
