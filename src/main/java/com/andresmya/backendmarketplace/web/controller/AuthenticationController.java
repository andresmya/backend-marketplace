package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.dto.request.AuthenticationRequest;
import com.andresmya.backendmarketplace.domain.dto.request.ResetPasswordRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdatePasswordRequest;
import com.andresmya.backendmarketplace.domain.dto.response.AuthenticationResponse;
import com.andresmya.backendmarketplace.domain.service.AuthenticationService;
import com.andresmya.backendmarketplace.domain.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws BadCredentialsException {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reset-password")
    public void requestRecoveryPassword(@RequestBody ResetPasswordRequest request) throws Exception{
        resetPasswordService.requestRecoveryPassword(request);
    }

    @PatchMapping("/reset-password")
    public void updatePassword(@RequestBody UpdatePasswordRequest request) throws Exception{
        resetPasswordService.updatePassword(request);
    }
}
