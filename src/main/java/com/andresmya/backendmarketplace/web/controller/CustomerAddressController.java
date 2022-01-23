package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerAddressRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCustomerAddressRequest;
import com.andresmya.backendmarketplace.domain.service.CustomerAddressService;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_CUSTOMER)
@RestController
@RequestMapping("/customers")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customerAddressService;

    @GetMapping("/{customerId}/addresses")
    public List<CustomerAddress> getAddressesByCustomerId(@PathVariable("customerId") Integer customerId) throws Exception{
        return customerAddressService.getCustomerAddressesByCustomerId(customerId);
    }

    @PostMapping("/{customerId}/addresses")
    public CustomerAddress createCustomerAddress(@PathVariable("customerId") Integer customerId,
                                                 @RequestBody CreateCustomerAddressRequest request) throws Exception{
        return customerAddressService.createCustomerAddress(customerId, request);
    }

    @PatchMapping("/{customerId}/addresses/{id}")
    public CustomerAddress updateCustomerAddress(@PathVariable("customerId") Integer customerId,
                                                 @PathVariable("id") Integer addressId,
                                                 @RequestBody UpdateCustomerAddressRequest request) throws Exception{
        return customerAddressService.updateCustomerAddress(customerId, addressId, request);
    }

    @DeleteMapping("/{customerId}/addresses/{id}")
    public void deleteCustomerAddress(@PathVariable("customerId") Integer customerId,
                                                 @PathVariable("id") Integer addressId) throws Exception{
        customerAddressService.deleteById(customerId, addressId);
    }

}
