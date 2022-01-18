package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IVendorRepository {
    Vendor createVendor(Vendor vendor);
    Vendor updateVendor(Vendor vendor);
    Optional<Vendor> getVendorById(Integer id);
    Optional<Vendor> getVendorByEmail(String email);
    Page<Vendor> getAllVendors(Pageable pageable);
    void deleteVendorById(Integer id);
}
