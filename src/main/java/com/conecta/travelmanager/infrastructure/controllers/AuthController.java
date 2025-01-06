package com.conecta.travelmanager.infrastructure.controllers;

import com.conecta.travelmanager.application.services.UserCreateUseCase;
import com.conecta.travelmanager.domain.enums.RoleName;
import com.conecta.travelmanager.domain.models.Role;
import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.RoleRepository;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import com.conecta.travelmanager.infrastructure.dto.AuthLoginRequest;
import com.conecta.travelmanager.infrastructure.dto.ErrorResponse;
import com.conecta.travelmanager.infrastructure.dto.RegisterClientRequest;
import com.conecta.travelmanager.infrastructure.services.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthController {
    private final UserDetailServiceImpl userDetailService;
    private final UserCreateUseCase userCreateUseCase;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserClientRepository userClientRepository;

    public AuthController(UserDetailServiceImpl userDetailService, UserCreateUseCase userCreateUseCase, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserClientRepository userClientRepository) {
        this.userDetailService = userDetailService;
        this.userCreateUseCase = userCreateUseCase;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userClientRepository = userClientRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        try {
            return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorResponse("Invalid Credentials"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterClientRequest userClientCreateRequest) {
        try {
            String password = userClientCreateRequest.password();
            String rePassword = userClientCreateRequest.rePassword();

            if (!password.equals(rePassword)) {
                return new ResponseEntity<>(new ErrorResponse("Passwords must be equal"), HttpStatus.BAD_REQUEST);
            }

            var userExists = this.userClientRepository.findByMail(userClientCreateRequest.email());
            if (userExists.isPresent()) {
                return new ResponseEntity<>(new ErrorResponse("User with this email already exists"), HttpStatus.BAD_REQUEST);
            }

            List<Role> roles = new ArrayList<>();
            for (RoleName roleName : userClientCreateRequest.roles()) {
                var role = roleRepository.findByName(roleName)
                        .orElse(null);

                if (role == null) {
                    return new ResponseEntity<>(new ErrorResponse("Role " + roleName + " does not exist"), HttpStatus.BAD_REQUEST);
                }
                roles.add(role);
            }

            UserClient defaultUserEntity = UserClient.builder()
                    .identification(userClientCreateRequest.identification())
                    .name(userClientCreateRequest.name())
                    .email(userClientCreateRequest.email())
                    .password(passwordEncoder.encode(userClientCreateRequest.password()))
                    .build();

            this.userCreateUseCase.execute(defaultUserEntity, roles);

            return new ResponseEntity<>(new ErrorResponse("User created successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
