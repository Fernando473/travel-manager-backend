package com.conecta.travelmanager.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import com.conecta.travelmanager.domain.models.UserClient;
import java.util.List;

@Data
@Builder
public class UserMeResponse {
    private String identification;
    private String email;
    private List<String> roles;

    public static UserMeResponse fromEntity(UserClient userClient, List<String> roles) {
        return UserMeResponse.builder()
                .identification(userClient != null ? userClient.getIdentification() : "")
                .email(userClient != null ? userClient.getEmail() : "")
                .roles(roles)
                .build();
    }
}
