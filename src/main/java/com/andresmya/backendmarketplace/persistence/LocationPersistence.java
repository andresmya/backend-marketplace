package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;
import com.andresmya.backendmarketplace.domain.repository.ILocationRepository;
import com.andresmya.backendmarketplace.persistence.jpa.repository.ICityJpaRepository;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IStateJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.ILocationPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationPersistence implements ILocationRepository {

    @Autowired
    private IStateJpaRepository stateJpaRepository;

    @Autowired
    private ICityJpaRepository cityJpaRepository;

    @Autowired
    private ILocationPersistenceMapper locationMapper;

    @Override
    public List<State> getAllStates() {
        return locationMapper.toStates(stateJpaRepository.findAll());
    }

    @Override
    public List<City> getAllCitiesByStateId(Integer id) {
        return locationMapper.toCities(cityJpaRepository.findByStateId(id));
    }

    @Override
    public Optional<City> getCityById(Integer id) {
        return cityJpaRepository.findById(id).map(cityEntity -> locationMapper.toCity(cityEntity));
    }
}
