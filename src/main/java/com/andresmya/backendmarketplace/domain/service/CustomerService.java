package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Customer;
import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCustomerRequest;
import com.andresmya.backendmarketplace.domain.mapper.ICustomerMapper;
import com.andresmya.backendmarketplace.domain.repository.ICustomerRepository;
import com.andresmya.backendmarketplace.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Autowired
    private UserService userService;

    public Customer updateCustomer(Integer customerId, UpdateCustomerRequest request) throws InvalidArgumentException, NotFoundException {
        checkArguments(request.getFirstName(), request.getLastName());
        Customer customer = customerRepository.getCustomerById(customerId).orElseThrow(() -> new NotFoundException("Customer ID "+ customerId));
        customer.setFirstName(request.getFirstName().toUpperCase());
        customer.setLastName(request.getLastName().toUpperCase());
        return customerRepository.updateCustomer(customer);
    }

    public Customer createCustomer(CreateCustomerRequest request) throws Exception {
        checkArguments(request.getFirstName(), request.getLastName());
        request.setFirstName(request.getFirstName().toUpperCase());
        request.setLastName(request.getLastName().toUpperCase());
        User user = userService.createUser(customerMapper.toCreateUserRequest(request));
        return customerRepository.createCustomer(customerMapper.toCustomer(request, user));
    }

    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.getAllCustomers(pageable);
    }

    public void deleteCustomerById(Integer customerId) throws NotFoundException {
        Customer customer = customerRepository.getCustomerById(customerId).orElseThrow(() -> new NotFoundException("Customer ID "+ customerId));
        User user = customer.getUser();
        customerRepository.deleteCustomerById(customerId);
        userService.deleteUser(user);
    }

    public Customer getCustomerById(Integer customerId) throws NotFoundException {
        return customerRepository.getCustomerById(customerId).orElseThrow(() -> new NotFoundException("Customer ID " + customerId));
    }

    protected Optional<Customer> getCustomerByUserId(Integer userId){
        return customerRepository.getCustomerByUserId(userId);
    }

    private void checkArguments(String firstName, String lastName) throws InvalidArgumentException {
       if (
               firstName == null ||
               lastName == null ||
               firstName.isEmpty() ||
               lastName.isEmpty()
       ) throw new InvalidArgumentException("First name / Last name");
    }

}
