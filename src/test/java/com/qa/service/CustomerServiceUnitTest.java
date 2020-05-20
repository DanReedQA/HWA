package com.qa.service;

import com.qa.domain.Box;
import com.qa.domain.Customer;
import com.qa.dto.BoxDTO;
import com.qa.dto.CustomerDTO;
import com.qa.exceptions.CustomerNotFoundException;
import com.qa.repo.CustomersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceUnitTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomersRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Customer> customerList;

    private Customer testCustomer;

    private long customerId = 1L;

    private Customer testCustomerWithID;

    private CustomerDTO customerDTO;

    private CustomerDTO mapToDTO(Customer customer){
        return this.mapper.map(customer, CustomerDTO.class);
    }

    @Before
    public void setUp() {
        this.customerList = new ArrayList<>();
        this.testCustomer = new Customer("Dan");
        this.customerList.add(testCustomer);
        this.testCustomerWithID = new Customer(testCustomer.getUsername());
        this.testCustomerWithID.setCustomerId(customerId);
        this.customerDTO = this.mapToDTO(testCustomerWithID);
    }

    @Test
    public void getAllCustomersTest(){
        when(repository.findAll()).thenReturn(this.customerList);
        when(this.mapper.map(testCustomerWithID, CustomerDTO.class)).thenReturn(customerDTO);
        assertFalse("Service returned no Customers", this.service.readCustomers().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createCustomerTest(){
        when(repository.save(testCustomer)).thenReturn(testCustomerWithID);
        when(this.mapper.map(testCustomerWithID, CustomerDTO.class)).thenReturn(customerDTO);
        assertEquals(this.service.createCustomer(testCustomer), this.customerDTO);
        verify(repository, times(1)).save(this.testCustomer);
    }

    @Test
    public void findCustomerByIdTest(){
        when(this.repository.findById(customerId)).thenReturn(java.util.Optional.ofNullable(testCustomerWithID));
        when(this.mapper.map(testCustomerWithID, CustomerDTO.class)).thenReturn(customerDTO);
        assertEquals(this.service.findCustomerById(this.customerId), customerDTO);
        verify(repository, times(1)).findById(customerId);
    }

    @Test
    public void deleteCustomerByExistingId(){
        when(this.repository.existsById(customerId)).thenReturn(true, false);
        assertFalse(service.deleteCustomer(customerId));
        verify(repository, times(1)).deleteById(customerId);
        verify(repository, times(2)).existsById(customerId);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void deleteCustomerByNonExistingId(){
        when(this.repository.existsById(customerId)).thenReturn(false);
        service.deleteCustomer(customerId);
        verify(repository, times(1)).existsById(customerId);
    }

    @Test
    public void updateCustomerTest(){

        Customer newCustomer = new Customer("admin");
        Customer updateCustomer = new Customer(newCustomer.getUsername());
        updateCustomer.setCustomerId(customerId);

        CustomerDTO updateCustomerDTO = new ModelMapper().map(updateCustomer, CustomerDTO.class);

        when(this.repository.findById(customerId)).thenReturn(java.util.Optional.ofNullable(testCustomerWithID));
        when(this.repository.save(updateCustomer)).thenReturn(updateCustomer);
        when(this.mapper.map(updateCustomer, CustomerDTO.class)).thenReturn(updateCustomerDTO);

        assertEquals(updateCustomerDTO, this.service.updateCustomer(customerId, newCustomer));
        verify(this.repository, times(1)).findById(customerId);
        verify(this.repository, times(1)).save(updateCustomer);
    }

}