package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.persistence.entity.CustomerAddressEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ILocationMapper.class)
public interface ICustomerAddressMapper {
    CustomerAddress toCustomerAddress(CustomerAddressEntity customerAddressEntity);
    List<CustomerAddress> toCustomerAddresses(List<CustomerAddressEntity> customerAddressEntities);
}
