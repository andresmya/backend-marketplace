package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.domain.repository.IVendorRepository;
import com.andresmya.backendmarketplace.persistence.entity.VendorEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IVendorJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IVendorPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class VendorPersistence implements IVendorRepository {

    @Autowired
    private IVendorJpaRepository vendorJpaRepository;

    @Autowired
    private IVendorPersistenceMapper vendorPersistenceMapper;

    @Override
    public Vendor createVendor(Vendor vendor) {
        VendorEntity vendorEntity = vendorJpaRepository.save(vendorPersistenceMapper.toVendorEntity(vendor));
        vendorEntity.setCreatedAt(new Date());
        vendorEntity.setUpdatedAt(new Date());
        return vendorPersistenceMapper.toVendor(vendorEntity);
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
        VendorEntity vendorEntity = vendorJpaRepository.save(vendorPersistenceMapper.toVendorEntity(vendor));
        return vendorPersistenceMapper.toVendor(vendorEntity);
    }

    @Override
    public Optional<Vendor> getVendorById(Integer id) {
        return vendorJpaRepository.findById(id).map(vendorEntity -> vendorPersistenceMapper.toVendor(vendorEntity));
    }

    @Override
    public Optional<Vendor> getVendorByUserId(Integer userId) {
        return vendorJpaRepository.findByUserId(userId).map(vendorEntity -> vendorPersistenceMapper.toVendor(vendorEntity));
    }

    @Override
    public Page<Vendor> getAllVendors(Pageable pageable) {
        return vendorJpaRepository.findAll(pageable).map(vendorEntity -> vendorPersistenceMapper.toVendor(vendorEntity));
    }

    @Override
    public void deleteVendorById(Integer id) {
        vendorJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return vendorJpaRepository.existsById(id);
    }
}
