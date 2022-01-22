package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.Customer;
import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerAddressRequest;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerRequest;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateUserRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Customer toCustomer(CreateCustomerRequest request, User user);

    @Mappings({
            @Mapping(target = "rolId", constant = "3")
    })
    CreateUserRequest toCreateUserRequest(CreateCustomerRequest request);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    CustomerAddress toCustomerAddress(CreateCustomerAddressRequest request, City city, Integer customerId);


}
