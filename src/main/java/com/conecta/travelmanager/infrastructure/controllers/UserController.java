package com.conecta.travelmanager.infrastructure.controllers;

import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import com.conecta.travelmanager.infrastructure.dto.ErrorResponse;
import com.conecta.travelmanager.infrastructure.dto.UserMeResponse;
import com.conecta.travelmanager.infrastructure.services.UserDetailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDetailServiceImpl userDetailService;
    private final UserClientRepository userClientRepository;

    public UserController(UserDetailServiceImpl userDetailService, UserClientRepository userClientRepository) {
        this.userDetailService = userDetailService;
        this.userClientRepository = userClientRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            var user = this.userDetailService.loadUserByUsername(username);
            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            
            var userClient = this.userClientRepository.findByMail(user.getUsername()).orElse(null);
            var response = UserMeResponse.fromEntity(userClient, roles);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
