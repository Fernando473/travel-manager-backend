package com.conecta.travelmanager.infrastructure.services;

import com.conecta.travelmanager.domain.models.UserClient;
import com.conecta.travelmanager.domain.repositories.UserClientRepository;
import com.conecta.travelmanager.infrastructure.dto.AuthLoginRequest;
import com.conecta.travelmanager.infrastructure.dto.AuthResponse;
import com.conecta.travelmanager.infrastructure.utils.jwt.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserClientRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(UserClientRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserClient userEntity = userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roleNames = userRepository.findRoleNamesByEmail(username);

        for (String roleName : roleNames) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                true, true, true, true,
                authorities
        );
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateToken(authentication);
        return new AuthResponse(username, "User loged succesfully", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
