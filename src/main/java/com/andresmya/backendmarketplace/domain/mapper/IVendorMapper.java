package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateUserRequest;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateVendorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IVendorMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Vendor toVendor(CreateVendorRequest request, User user);

    @Mappings({
            @Mapping(target = "rolId", constant = "2")
    })
    CreateUserRequest toCreateUserRequest(CreateVendorRequest request);
}
