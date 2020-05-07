package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Customer;
import com.qa.dto.CustomerDTO;
import com.qa.repo.CustomersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private CustomersRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Customer testCustomer;

    private Customer testCustomerWithID;

    private long customerId;

    private CustomerDTO customerDTO;

    private CustomerDTO mapToDTO(Customer customer){
        return this.mapper.map(customer, CustomerDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testCustomer = new Customer("Dan", "Reed");
        this.testCustomerWithID = this.repository.save(testCustomer);
        this.customerId = testCustomerWithID.getCustomerId();
        this.customerDTO = this.mapToDTO(testCustomerWithID);
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllCustomers")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(customerDTOList));
    }

    @Test
    public void getCustomerByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getCardById/" + this.customerId)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(customerDTO));
    }

    @Test
    public void createCustomerTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testCustomer))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(customerDTO));
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteCustomer/" + this.customerId)
        ).andExpect(status().isNoContent());
    }
}