package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.Vendor;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateVendorRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateVendorRequest;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import com.andresmya.backendmarketplace.domain.service.VendorService;
import com.andresmya.backendmarketplace.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ProductController productController;

    @GetMapping()
    public ResponseEntity<Page<Vendor>> getVendors(@RequestParam("page") int page, @RequestParam("size")int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<Page<Vendor>>(vendorService.getVendors(pageable), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @PostMapping()
    public ResponseEntity<Vendor> createVendor(@RequestBody CreateVendorRequest request) throws Exception {
        return new ResponseEntity<Vendor>(vendorService.createVendor(request), HttpStatus.CREATED);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_VENDOR)
    @GetMapping("/{vendorId}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("vendorId") Integer vendorId) throws NotFoundException {
        return new ResponseEntity<Vendor>(vendorService.getVendorById(vendorId), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_VENDOR)
    @PatchMapping("/{vendorId}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("vendorId") Integer vendorId,
                                               @RequestBody UpdateVendorRequest request,
                                               Authentication authentication) throws Exception {
        return new ResponseEntity<Vendor>(vendorService.updateVendor(vendorId, request), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_VENDOR)
    @DeleteMapping("/{vendorId}")
    public void deleteVendor(@PathVariable("vendorId") Integer vendorId) throws NotFoundException {
        vendorService.deleteVendorById(vendorId);
    }

    @GetMapping("/{vendorId}/products")
    public ResponseEntity<Page<Product>> getProductsByVendorId(@RequestParam("page") int page,
                                                                 @RequestParam("size")int size,
                                                                 @PathVariable("vendorId") Integer vendorId){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productController.getProductsByVendorId(pageable, vendorId), HttpStatus.OK);
    }
}
