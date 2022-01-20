package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;
import com.andresmya.backendmarketplace.domain.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<State>> getAllStates() {
        return new ResponseEntity<>(locationService.getAllStates(), HttpStatus.OK);
    }

    @GetMapping("/{stateId}/cities")
    public ResponseEntity<List<City>> getAllCitiesByStateId(@PathVariable("stateId") Integer stateId) {
        return new ResponseEntity<>(locationService.getAllCitiesByStateId(stateId), HttpStatus.OK);
    }
}
