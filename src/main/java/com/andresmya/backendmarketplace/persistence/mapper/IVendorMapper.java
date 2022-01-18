package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.persistence.entity.VendorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVendorMapper {
    Vendor toVendor(VendorEntity vendorEntity);
    List<Vendor> toVendors(List<VendorEntity> vendorEntities);
}
