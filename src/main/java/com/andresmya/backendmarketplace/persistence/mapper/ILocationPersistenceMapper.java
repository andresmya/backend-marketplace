package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.State;
import com.andresmya.backendmarketplace.persistence.entity.CityEntity;
import com.andresmya.backendmarketplace.persistence.entity.StateEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILocationPersistenceMapper {
    List<State> toStates(List<StateEntity> stateEntities);

    City toCity(CityEntity city);
    List<City> toCities(List<CityEntity> cityEntities);

    @InheritInverseConfiguration
    CityEntity toCityEntity(City city);
}
