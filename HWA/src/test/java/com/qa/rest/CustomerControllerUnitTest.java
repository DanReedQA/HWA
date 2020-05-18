package com.qa.rest;

import com.qa.domain.Customer;
import com.qa.dto.CustomerDTO;
import com.qa.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerUnitTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService service;

    private List<Customer> customers;

    private Customer testCustomer;

    private Customer testCustomerWitId;

    private long customerId = 1L;

    private CustomerDTO customerDTO;

    private final ModelMapper mapper = new ModelMapper();

    private CustomerDTO mapToDTO(Customer customer){
        return this.mapper.map(customer, CustomerDTO.class);
    }

    @Before
    public void setUp(){
        this.customers = new ArrayList<>();
        this.testCustomer = new Customer("Dan");
        this.customers.add(testCustomer);
        this.testCustomerWitId = new Customer(testCustomer.getUsername());
        this.testCustomerWitId.setCustomerId(this.customerId);
        this.customerDTO = this.mapToDTO(testCustomerWitId);
    }

    @Test
    public void getAllCustomersTest(){
        when(service.readCustomers()).thenReturn(this.customers.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No customers found", this.customerController.getAllCustomers().getBody().isEmpty());
        verify(service, times(1)).readCustomers();
    }

    @Test
    public void createCustomerTest(){
        when(this.service.createCustomer(testCustomer)).thenReturn(this.customerDTO);
        assertEquals(this.customerController.createCustomer(testCustomer), new ResponseEntity<CustomerDTO>(this.customerDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createCustomer(testCustomer);
    }

    @Test
    public void deleteCustomerTestFalse(){
        this.customerController.deleteCustomer(customerId);
        verify(service, times(1)).deleteCustomer(customerId);
    }


    @Test
    public void deleteCustomerTestTrue(){
        when(service.deleteCustomer(3L)).thenReturn(true);
        this.customerController.deleteCustomer(3L);
        verify(service, times(1)).deleteCustomer(3L);
    }

    @Test
    public void getCustomerByIDTest(){
        when(this.service.findCustomerById(customerId)).thenReturn(this.customerDTO);
        assertEquals(this.customerController.getCustomerById(customerId), new ResponseEntity<CustomerDTO>(this.customerDTO, HttpStatus.OK));
        verify(service, times(1)).findCustomerById(customerId);
    }
}