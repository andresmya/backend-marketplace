package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;
import com.andresmya.backendmarketplace.domain.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private ILocationRepository locationRepository;

    public List<State> getAllStates() {
        return locationRepository.getAllStates();
    }

    public List<City> getAllCitiesByStateId(Integer stateId) {
        return locationRepository.getAllCitiesByStateId(stateId);
    }

    protected Optional<City> getCityById(Integer id){
        return locationRepository.getCityById(id);
    }
}
