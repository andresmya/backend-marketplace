package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.dto.response.MemoryStatsResponse;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import com.andresmya.backendmarketplace.domain.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class UtilController {

    @Autowired
    private UtilService utilService;

    @RequestMapping(value = "/health",method = RequestMethod.HEAD)
    public void apiHealth(){}

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @GetMapping("/memory-stats")
    public ResponseEntity<MemoryStatsResponse> getMemoryStats() {
        return new ResponseEntity<>(utilService.getMemoryStats(), HttpStatus.OK);
    }

}
