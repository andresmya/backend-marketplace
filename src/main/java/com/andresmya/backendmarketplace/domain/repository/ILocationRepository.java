package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;

import java.util.List;

public interface ILocationRepository {
    List<State> getAllStates();
    List<City> getAllCitiesByStateId(Integer id);
}
