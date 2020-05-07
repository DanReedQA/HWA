package com.qa.rest;

import com.qa.domain.Customer;
import com.qa.dto.CustomerDTO;
import com.qa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(this.service.readCustomers());
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<CustomerDTO>(this.service.createCustomer(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return this.service.deleteCustomer(customerId)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.service.findCustomerById(customerId));
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCard(@PathVariable Long customerId, @RequestBody Customer customer) {
        return ResponseEntity.ok(this.service.updateCustomer(customerId, customer));
    }

    @PutMapping("/updateCustomer2")
    public ResponseEntity<CustomerDTO> updateCustomer2(@PathParam("customerId") Long customerId, @RequestBody Customer customer) {
        return ResponseEntity.ok(this.service.updateCustomer(customerId, customer));
    }

}
