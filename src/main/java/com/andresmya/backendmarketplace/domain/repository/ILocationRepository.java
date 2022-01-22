package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;

import java.util.List;
import java.util.Optional;

public interface ILocationRepository {
    List<State> getAllStates();
    List<City> getAllCitiesByStateId(Integer id);
    Optional<City> getCityById(Integer id);
}
