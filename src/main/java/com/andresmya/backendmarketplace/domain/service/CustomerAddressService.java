package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.City;
import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCustomerAddressRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCustomerAddressRequest;
import com.andresmya.backendmarketplace.domain.mapper.ICustomerMapper;
import com.andresmya.backendmarketplace.domain.repository.ICustomerAddressRepository;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {

    @Autowired
    private ICustomerAddressRepository customerAddressRepository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CustomerService customerService;

    public CustomerAddress createCustomerAddress(Integer customerId, CreateCustomerAddressRequest request) throws Exception{
        if (customerService.existsById(customerId)){
            City city = locationService.getCityById(request.getCityId())
                    .orElseThrow(() -> new NotFoundException("City ID " + request.getCityId()));
            return customerAddressRepository.createCustomerAddress(customerMapper.toCustomerAddress(request, city, customerId));
        }
        throw new InvalidArgumentException("Customer ID " + customerId);
    }

    public CustomerAddress updateCustomerAddress(Integer customerId, Integer addressId, UpdateCustomerAddressRequest request) throws Exception {
        CustomerAddress customerAddress = getById(addressId);
        if (!customerAddress.getCustomerId().equals(customerId)) throw new NotFoundException("Customer ID " + customerId + " does not have Address ID " + addressId);
            customerAddress.setAddress(request.getAddress() == null ? customerAddress.getAddress() : request.getAddress());
            customerAddress.setZipCode(request.getZipCode() == null ? customerAddress.getZipCode() : request.getZipCode());
            return customerAddressRepository.updateCustomerAddress(customerAddress);
    }

    public CustomerAddress getById(Integer id) throws Exception{
        return customerAddressRepository.getCustomerAddressById(id).orElseThrow(() -> new NotFoundException("Customer address ID " + id));
    }

    public List<CustomerAddress> getCustomerAddressesByCustomerId(Integer customerId) throws Exception{
        if(!customerService.existsById(customerId)) throw new NotFoundException("Customer ID " + customerId);
        return customerAddressRepository.getCustomerAddressesByCustomerId(customerId).orElse(null);
    }

    public void deleteById(Integer customerId, Integer id) throws  Exception{
        CustomerAddress customerAddress = getById(id);
        if (!customerAddress.getCustomerId().equals(customerId)) throw new NotFoundException("Customer ID " + customerId + " does not have Address ID " + id);
        customerAddressRepository.deleteCustomerAddressById(id);

    }
}
