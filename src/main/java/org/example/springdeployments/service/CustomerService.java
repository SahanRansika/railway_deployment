package org.example.springdeployments.service;

import org.example.springdeployments.entity.Customer;
import org.example.springdeployments.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    // Get all customers
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found"));
    }

    // Save a new customer
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(Customer customer) {
        Customer existing = customerRepo.findById(customer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Only update fields that changed
        existing.setFirstname(customer.getFirstname());
        existing.setLastname(customer.getLastname());

        return customerRepo.save(existing); // Hibernate will handle versioning
    }


    // Delete customer by ID
    public void deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete: Customer with ID " + id + " not found");
        }
        customerRepo.deleteById(id);
    }
}
