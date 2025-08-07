package org.example.springdeployments.service;

import org.example.springdeployments.entity.Customer;
import org.example.springdeployments.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepo.findById(id).orElse(null);
    }
    public Customer createCustomer(Customer customer) {
        System.out.println("Creating customer: " + customer.getFirstName());
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        System.out.println(customer.getId());
        Optional<Customer> byId = customerRepo.findById(customer.getId());
        byId.ifPresent(existingCustomer -> {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
        });
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepo.deleteById(id);
    }
}
