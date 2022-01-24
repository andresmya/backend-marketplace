package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.Customer;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCustomerRequest;
import com.andresmya.backendmarketplace.domain.service.CustomerService;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @GetMapping()
    public ResponseEntity<Page<Customer>> getCustomers(@RequestParam("page") int page, @RequestParam("size")int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<Page<Customer>>(customerService.getCustomers(pageable), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest request) throws Exception {
        return new ResponseEntity<Customer>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_CUSTOMER)
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) throws Exception {
        return new ResponseEntity<Customer>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_CUSTOMER)
    @PatchMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody UpdateCustomerRequest request) throws Exception {
        return new ResponseEntity<Customer>(customerService.updateCustomer(customerId, request), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_CUSTOMER)
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) throws NotFoundException {
        customerService.deleteCustomerById(customerId);
    }
}
