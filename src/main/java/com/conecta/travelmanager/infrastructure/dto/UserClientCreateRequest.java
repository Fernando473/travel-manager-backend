package com.conecta.travelmanager.infrastructure.dto;

import lombok.Builder;

@Builder
public record UserClientCreateRequest(String name, String identification, String email) {
}
