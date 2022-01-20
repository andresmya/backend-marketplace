package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.persistence.entity.VendorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IRolePersistenceMapper.class)
public interface IVendorPersistenceMapper {
    Vendor toVendor(VendorEntity vendorEntity);
    List<Vendor> toVendors(List<VendorEntity> vendorEntities);

    @InheritInverseConfiguration
    VendorEntity toVendorEntity(Vendor vendor);
}
