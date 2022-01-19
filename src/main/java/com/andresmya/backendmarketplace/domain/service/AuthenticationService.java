package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.dto.request.AuthenticationRequest;
import com.andresmya.backendmarketplace.domain.dto.response.AuthenticationResponse;
import com.andresmya.backendmarketplace.web.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(jwt, JwtUtil.getNewExpirationDate(), "username", new Role());
        return response;
    }


}
