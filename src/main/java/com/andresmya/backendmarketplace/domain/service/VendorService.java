package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateVendorRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateVendorRequest;
import com.andresmya.backendmarketplace.domain.mapper.IVendorMapper;
import com.andresmya.backendmarketplace.domain.repository.IVendorRepository;
import com.andresmya.backendmarketplace.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private IVendorRepository vendorRepository;

    @Autowired
    private IVendorMapper vendorMapper;

    @Autowired
    private UserService userService;

    public Page<Vendor> getVendors(Pageable pageable) {
        return vendorRepository.getAllVendors(pageable);
    }

    public Vendor getVendorById(Integer vendorId) throws NotFoundException {
        return vendorRepository.getVendorById(vendorId).orElseThrow(() -> new NotFoundException("Vendor ID " + vendorId));
    }

    public Vendor createVendor(CreateVendorRequest request) throws Exception {
        checkArguments(request.getName());
        request.setName(request.getName().toUpperCase());
        User user = userService.createUser(vendorMapper.toCreateUserRequest(request));
        return vendorRepository.createVendor(vendorMapper.toVendor(request, user));
    }

    public Vendor updateVendor(Integer vendorId, UpdateVendorRequest request) throws Exception {
        checkArguments(request.getName());
        Vendor vendor = vendorRepository.getVendorById(vendorId).orElseThrow(() -> new NotFoundException("Vendor ID "+ vendorId));
        vendor.setName(request.getName().toUpperCase());
        return vendorRepository.updateVendor(vendor);
    }

    public void deleteVendorById(Integer vendorId) throws NotFoundException {
        Vendor vendor = vendorRepository.getVendorById(vendorId).orElseThrow(() -> new NotFoundException("Vendor ID "+ vendorId));
        User user = vendor.getUser();
        vendorRepository.deleteVendorById(vendorId);
        userService.deleteUser(user);
    }

    protected boolean existsById(Integer vendorId){
        return vendorRepository.existsById(vendorId);
    }

    private void checkArguments(String name) throws InvalidArgumentException {
        if (
                name == null || name.isEmpty()
        ) throw new InvalidArgumentException("Empty name");
    }

}
