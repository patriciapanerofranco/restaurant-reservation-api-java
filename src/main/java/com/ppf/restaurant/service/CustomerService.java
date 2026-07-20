package com.ppf.restaurant.service;

import com.ppf.restaurant.dto.CreateCustomerRequest;
import com.ppf.restaurant.dto.CustomerResponse;
import com.ppf.restaurant.entity.Customer;
import com.ppf.restaurant.exception.ResourceNotFoundException;
import com.ppf.restaurant.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = getCustomerEntityById(id);
        return mapToResponse(customer);
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    public Customer getCustomerEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}