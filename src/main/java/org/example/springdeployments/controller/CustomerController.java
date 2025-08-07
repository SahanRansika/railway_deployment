package org.example.springdeployments.controller;

import org.example.springdeployments.entity.Customer;
import org.example.springdeployments.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/all")
    public List<Customer> findAll() {
        return customerService.getAllCustomers();
    }
    @GetMapping
    public Customer findById(Integer id) {
        return customerService.getCustomerById(id);
    }
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    @DeleteMapping
    public void delete(@RequestBody Integer id) {
        customerService.deleteCustomer(id);
    }
}
