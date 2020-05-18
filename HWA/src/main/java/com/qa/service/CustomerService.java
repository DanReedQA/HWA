package com.qa.service;

import com.qa.domain.Customer;
import com.qa.dto.CustomerDTO;
import com.qa.exceptions.CustomerNotFoundException;
import com.qa.repo.CustomersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomersRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomersRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private CustomerDTO mapToDTO(Customer customer) {
        return this.mapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> readCustomers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(Customer customer) {
        Customer tempCustomer = this.repo.save(customer);
        return this.mapToDTO(tempCustomer);
    }

    public CustomerDTO findCustomerById(Long customerId) {
        return this.mapToDTO(this.repo.findById(customerId).orElseThrow(CustomerNotFoundException::new));
    }

    public CustomerDTO updateCustomer(Long customerId, Customer customer) {
        Customer update = this.repo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        update.setUsername(customer.getUsername());
        Customer tempCustomer = this.repo.save(update);
        return this.mapToDTO(tempCustomer);
    }

    public boolean deleteCustomer(Long customerId){
        if(!this.repo.existsById(customerId)){
            throw new CustomerNotFoundException();
        }
        this.repo.deleteById(customerId);
        return this.repo.existsById(customerId);
    }


}